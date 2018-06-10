package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.domain.guest.contact.Contact;

/**
 * Roomix
 * at.fhv.roomix.implement.mapping
 * ContactMapping
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactMapping implements MapType<Contact, ContactPojo> {
    @Override
    public void map(Contact source, ContactPojo destination, Mapper mapper) throws MappingException {
        destination.setCompanyName(source.getCompanyName());
        destination.setContactId(source.getId());
        destination.setCountry(source.getCountry());
        destination.setEmail(source.getEmail());
        destination.setFirstName(source.getFirstName());
        destination.setLastName(source.getLastName());
        destination.setHouseNumber(source.getHouseNumber());
        destination.setPostcode(source.getPostcode());
        destination.setPlace(source.getPlace());
        destination.setStreet(source.getStreet());
        destination.setPhoneNumber(source.getPhoneNumber());
    }

    @Override
    public void mapReverse(ContactPojo source, Contact destination, Mapper mapper) throws MappingException {
        destination.setCompanyName(source.getCompanyName());
        destination.setId(source.getContactId());
        destination.setCountry(source.getCountry());
        destination.setEmail(source.getEmail());
        destination.setFirstName(source.getFirstName());
        destination.setLastName(source.getLastName());
        destination.setHouseNumber(source.getHouseNumber());
        destination.setPostcode(source.getPostcode());
        destination.setPlace(source.getPlace());
        destination.setStreet(source.getStreet());
        destination.setPhoneNumber(source.getPhoneNumber());
    }
}
