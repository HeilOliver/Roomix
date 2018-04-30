package at.fhv.roomix.persistlayer;

import at.fhv.roomix.persistlayer.builder.ContactBuilder;
import at.fhv.roomix.persistlayer.dataaccess.ContactFactory;
import at.fhv.roomix.persistlayer.dataaccess.EntityFactory;
import at.fhv.roomix.persistlayer.domain.Contact;
import at.fhv.roomix.persistlayer.domain.ContactNote;
import at.fhv.roomix.persistlayer.exception.*;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.persistlayer
 * RunMe
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RunMe {

    public static void main(String[] args) throws PersistException {


        Contact contact1 = ContactBuilder.getContact(1);
        ContactNote contactNote = new ContactNote();

        for (ContactNote note : contact1.getNotes()) {
            note.setContent("ZU was anderem");
            break;
        }

        contactNote.setContent("Test Notiz");
        contact1.getNotes().add(contactNote);
        ContactBuilder.update(contact1);

        Contact contact = new Contact();
        contact.setCountry("dadadad");
        contact.setStreet("dsfdsf");
        contact.setPhoneNumber("1211211212");
        contact.setEmail("sdadaa@dss.com");
        contact.setCompanyName("sdadadadda");
        contact.setLastName("sadadad");
        contact.setFirstName("sadaad");
        contact.setHouseNumber("s123");
        contact.setPlace("fdfssf");
        contact.setPostcode("sdads");
        contact.getNotes().add(contactNote);
        ContactBuilder.update(contact);

        EntityFactory.commitAll();

        Collection<Contact> contacts = ContactBuilder.getContacts();

        contacts.forEach((e) -> System.out.println(e.getFirstName() + " - " + e.getLastName()));
    }
}
