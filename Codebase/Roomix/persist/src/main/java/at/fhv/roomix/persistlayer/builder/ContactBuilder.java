package at.fhv.roomix.persistlayer.builder;

import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persist.model.ContactNoteEntity;
import at.fhv.roomix.persistlayer.dataaccess.ContactFactory;
import at.fhv.roomix.persistlayer.domain.Contact;
import at.fhv.roomix.persistlayer.domain.ContactNote;
import at.fhv.roomix.persistlayer.exception.BuilderCreateException;
import at.fhv.roomix.persistlayer.exception.PersistLoadException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import javax.print.attribute.standard.Destination;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.builder
 * ContactBuilder
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactBuilder {
    private static TypeMap<ContactEntity, Contact> typeMap;

    private static TypeMap<ContactEntity, Contact> converterFromEntity() {
        if (typeMap != null) return typeMap;
        ModelMapper modelMapper = new ModelMapper();
        typeMap = modelMapper.createTypeMap(ContactEntity.class, Contact.class);
        typeMap.addMappings(mapper -> {
            mapper.using((ctx) -> ctx.getSource() == null)
                    .map(ContactEntity::getContractingPartiesByContactId, Contact::setContractingParty);

        });
        return typeMap;
    }

    public static Contact getContact(int id) throws PersistLoadException {
        ContactEntity entity = ContactFactory.getInstance().get(id);
        return converterFromEntity().map(entity);
    }

    public static Collection<Contact> getContacts() throws PersistLoadException {
        Collection<ContactEntity> all = ContactFactory.getInstance().getAll();
        Collection<Contact> results = new HashSet<>();

        all.forEach((e) -> results.add(converterFromEntity().map(e)));
        return results;
    }

    public static void update(Contact obj) throws BuilderCreateException {
        ContactEntity entity;
        try {
            entity = obj.getId() == 0 ? new ContactEntity()
                    : ContactFactory.getInstance().get(obj.getId());
        } catch (PersistLoadException e) {
            throw new BuilderCreateException(e);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Contact, ContactEntity>() {
            @Override
            protected void configure() {
                skip().setContactNotes(null);
            }
        });
        modelMapper.addMappings(new PropertyMap<ContactNote, ContactNoteEntity>() {
            @Override
            protected void configure() {
                skip().setContact(null);
            }
        });

        modelMapper.map(obj, entity);

        /* Update */
        entity.getContactNotes()
                .forEach((noteEntity)-> obj.getNotes().stream()
                        .filter((dn) -> dn.getId() == noteEntity.getContactNoteId())
                        .findFirst().ifPresent(
                                (up) -> modelMapper.map(up, noteEntity)));

        /* Remove */
        entity.getContactNotes().removeIf(
                (n) -> obj.getNotes().stream().noneMatch(
                        (e) -> e.getId() == n.getContactNoteId()));

        /* Create New */
        Set<ContactNoteEntity> newEntry = obj.getNotes().stream().filter((n) -> n.getId() == 0)
                .map((n) -> modelMapper.map(n, ContactNoteEntity.class))
                .collect(Collectors.toSet());
        newEntry.forEach((e) -> e.setContact(entity));
        entity.getContactNotes().addAll(newEntry);

        ContactFactory.getInstance().saveOrUpdate(entity);
    }
}
