package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.persist.dataaccess.factory.PersonFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ContactEntity;
import at.fhv.roomix.persist.models.PersonEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * GuestBuilder
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class GuestBuilder {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<PersonEntity, Person>() {
            @Override
            protected void configure() {
                skip().setContact(null);
            }
        });
        mapper.addMappings(new PropertyMap<Person, PersonEntity>() {
            @Override
            protected void configure() {
                skip().setContact(null);
            }
        });
    }

    // An Method that wraps all exception in an RT Exception
    static PersonEntity updateGuestsUC(Person person) throws RuntimeException {
        try {
            return updateGuests(person);
        } catch (BuilderUpdateException e) {
            throw new RuntimeException(e);
        }
    }

    public static Person getPerson(int id) throws BuilderLoadException {
        if (id == 0) return new Person();
        PersonEntity entity = null;
        try {
            entity = PersonFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }

        Contact contact = null;
        if (entity.getContact() != null) {
            contact = ContactBuilder.getContact(entity.getContact().getContactId());
        }
        Person person = mapper.map(entity, Person.class);
        person.setContact(contact);

        return person;
    }

    static PersonEntity updateGuests(Person person) throws BuilderUpdateException {
        PersonEntity entity = PersonFactory.getInstance().getOrDefault(person.getId(), new PersonEntity());
        ContactEntity contactEntity = null;
        if (person.getContact() != null) {
            contactEntity = ContactBuilder.updateEntity(person.getContact());
        }
        mapper.map(person, entity);

        entity.setContact(contactEntity);

        PersonFactory.getInstance().saveOrUpdate(entity);
        return entity;
    }

    static Person getPersonUC(int id) throws RuntimeException {
        try {
            return getPerson(id);
        } catch (BuilderLoadException e) {
            throw new RuntimeException(e);
        }
    }
}
