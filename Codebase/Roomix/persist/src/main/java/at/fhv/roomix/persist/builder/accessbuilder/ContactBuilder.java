package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.guest.contact.ContactNote;
import at.fhv.roomix.persist.dataaccess.factory.ContactFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ContactEntity;
import at.fhv.roomix.persist.models.ContactNoteEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
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
                    .map(ContactEntity::getContractingParty, Contact::setHasContractingParty);

        });
        return typeMap;
    }

    public static Contact getContact(int id) throws BuilderLoadException {
        if (id == 0) return new Contact();
        ContactEntity entity = null;
        try {
            entity = ContactFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        return converterFromEntity().map(entity);
    }

    public static Collection<Contact> getContacts() throws BuilderLoadException {
        Collection<ContactEntity> all = null;
        try {
            all = ContactFactory.getInstance().getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        Collection<Contact> results = new HashSet<>();

        all.forEach((e) -> results.add(converterFromEntity().map(e)));
        return results;
    }

    public static void update(Contact obj) throws BuilderUpdateException {
        updateEntity(obj);
    }

    static ContactEntity updateEntity(Contact obj) throws BuilderUpdateException {
        ContactEntity entity;
        try {
            entity = obj.getId() == 0 ? new ContactEntity()
                    : ContactFactory.getInstance().get(obj.getId());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException(e);
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
                .forEach((noteEntity) -> obj.getNotes().stream()
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
        return entity;
    }
}
