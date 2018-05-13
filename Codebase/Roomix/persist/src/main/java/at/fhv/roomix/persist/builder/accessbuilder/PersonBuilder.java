package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.persist.dataaccess.factory.ContactFactory;
import at.fhv.roomix.persist.dataaccess.factory.PersonFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.mappings.PersonMapping;
import at.fhv.roomix.persist.models.ContactEntity;
import at.fhv.roomix.persist.models.PersonEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.accessbuilder
 * PersonBuilder
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersonBuilder {
    private static final Mapper mapper = Mapper.getInstance();

    static {
        Mapper.getInstance().addMapType(new PersonMapping(), PersonEntity.class, Person.class);
    }

    public static Collection<Person> getAll() throws BuilderLoadException {
        List<ContactEntity> all = null;
        try {
            all = ContactFactory.getInstance().getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e);
        }

        // Filter all TravelAgents and Company's
        Set<ContactEntity> entities = all.stream().filter((c) -> c.getContractingParty() == null ||
                c.getContractingParty().getContractingPartyType().equals("INDIVIDUAL")).collect(Collectors.toSet());

        // Make Persons out of any Contact but dont save them.
        // So the User can select one and send it for saving
        HashSet<Person> persons = new HashSet<>();
        for (ContactEntity contactEntity : entities) {
            Collection<PersonEntity> people = contactEntity.getPeople();
            if (people == null) {
                Person person = new Person();
                person.setFirstName(contactEntity.getFirstName());
                person.setLastName(contactEntity.getLastName());
                person.setContact(mapper.map(contactEntity, Contact.class));
                persons.add(person);
                continue;
            }
            people.forEach((p) -> persons.add(mapper.map(p, Person.class)));
        }

        return persons;
    }

    public static Person get(int personId) throws BuilderLoadException {
        if (personId == 0) return new Person();
        PersonEntity entity;
        try {
            entity = PersonFactory.getInstance().get(personId);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e);
        }
        return mapper.map(entity, Person.class);
    }

    public static void update(Person person) throws BuilderUpdateException {
        PersonEntity entity;
        try {
            entity = person.getId() == 0 ? new PersonEntity()
                    : PersonFactory.getInstance().get(person.getId());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException(e);
        }

        Contact contact = person.getContact();
        ContactEntity contactEntity = null;
        if (contact != null) {
            contactEntity = ContactBuilder.updateEntity(contact);
            contactEntity.getPeople().add(entity);
        }
        entity.setContact(contactEntity);

        PersonFactory.getInstance().saveOrUpdate(entity);
    }
}
