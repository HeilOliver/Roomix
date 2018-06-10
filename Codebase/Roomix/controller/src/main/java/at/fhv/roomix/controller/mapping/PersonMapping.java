package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.persist.builder.accessbuilder.PersonBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;

/**
 * Roomix
 * at.fhv.roomix.implement.mapping
 * PersonMapping
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersonMapping implements MapType<Person, PersonPojo> {
    static {
        Mapper.getInstance().addMapType(new ContactMapping(), Contact.class, ContactPojo.class);
    }

    @Override
    public void map(Person source, PersonPojo destination, Mapper mapper) throws MappingException {
        destination.setForeName(source.getFirstName());
        destination.setLastName(source.getLastName());
        destination.setId(source.getId());
        destination.setVip(source.isVip());

        if (source.getContact() != null) {
            ContactPojo contactPojo = mapper.map(source.getContact(), ContactPojo.class);
            destination.setContact(contactPojo);
        }
    }

    @Override
    public void mapReverse(PersonPojo source, Person destination, Mapper mapper) throws MappingException {
        destination.setFirstName(source.getForeName());
        destination.setLastName(source.getLastName());
        destination.setVip(source.isVip());

        if (source.getContact() != null) {
            if (destination.getContact() == null)
                destination.setContact(new Contact());

            mapper.map(source.getContact(), destination.getContact());
        }
    }
}
