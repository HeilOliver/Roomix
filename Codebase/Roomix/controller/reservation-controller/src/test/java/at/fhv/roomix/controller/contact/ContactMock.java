package at.fhv.roomix.controller.contact;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.rommix.controller.contact
 * ReservationMock
 * 18.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ContactMock implements IContactController {
    private static final Object lock = new Object();
    private static ContactMock instance;
    private Collection<ContactPojo> contactPojos = new HashSet<>();

    ContactMock() {
        ContactPojo pojo = new ContactPojo();
        pojo.setFirstName("Oliver");
        pojo.setLastName("Heil");
        pojo.setCountry("Germany");
        pojo.setPhoneNumber("+4312132132132");
        pojo.setEmail("Some@some.com");
        pojo.setPlace("Dornbirn");
        pojo.setPostcode("8505");
        pojo.setStreet("SomeStreet 4");
        contactPojos.add(pojo);

        pojo = new ContactPojo();
        pojo.setFirstName("Max");
        pojo.setLastName("Mustermann");
        pojo.setCountry("Germany");
        pojo.setPhoneNumber("+4312132132132");
        pojo.setEmail("Some@some.com");
        pojo.setPlace("Dornbirn");
        pojo.setPostcode("8505");
        pojo.setStreet("SomeStreet 4");
        contactPojos.add(pojo);
    }

    public static ContactMock getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ContactMock();
            }
        }
        return instance;
    }

    public void newContact(long sessionId, ContactPojo contactPojo)
            throws SessionFaultException, ValidationFault, ArgumentFaultException {
        contactPojos.add(contactPojo);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<ContactPojo> getAllContacts(long sessionId) throws SessionFaultException {
        return new HashSet<>(contactPojos);
    }

    @Override
    public Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException {
        return null;
    }

    @Override
    public void updateContact(long sessionId, ContactPojo contactPojo)
            throws SessionFaultException, ValidationFault, ArgumentFaultException {
        contactPojos.add(contactPojo);
    }
}
