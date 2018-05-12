package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.persist.builder.accessbuilder.ContactBuilder;
import at.fhv.roomix.persist.models.ContactEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * ContactMapping
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactMapping implements MapType<ContactEntity, Contact> {

    @Override
    public void map(ContactEntity source, Contact destination, Mapper mapper) throws MappingException {
        ContactBuilder.converterFromEntity().map(source, destination);
    }
}
