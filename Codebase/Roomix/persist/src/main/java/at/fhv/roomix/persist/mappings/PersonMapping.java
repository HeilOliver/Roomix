package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.reservation.Arrangement;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.persist.models.ArticleEntity;
import at.fhv.roomix.persist.models.ContactEntity;
import at.fhv.roomix.persist.models.PersonEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.accessbuilder
 * PersonMapping
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersonMapping implements MapType<PersonEntity,Person> {
    static {
        Mapper.getInstance().addMapType(new ContactMapping(), ContactEntity.class, Contact.class);
    }

    @Override
    public void map(PersonEntity source, Person destination, Mapper mapper) throws MappingException {
        destination.setId(source.getPersonId());
        destination.setFirstName(source.getFirstName());
        destination.setLastName(source.getLastName());
        destination.setVip(source.getIsVip() == 0);

        if (source.getContact() != null) {
            destination.setContact(mapper.map(source.getContact(), Contact.class));
        }
    }
}
