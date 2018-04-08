package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ContactPojo;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.ui.mocks
 * ReservationMock
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ReservationMock implements IReservationController {
    private static final Object lock = new Object();
    private static ReservationMock instance;

    ReservationMock() {
        ContactPojo pojo = new ContactPojo();
        pojo.setFname("Oliver");
        pojo.setLname("Heil");
        pojo.setCountry("Germany");
        pojo.setPhoneNumber("+4312132132132");
        pojo.setEmail("Some@some.com");
        pojo.setPlace("Dornbirn");
        pojo.setPostcode("8505");
        pojo.setStreet("SomeStreet 4");
        contactPojos.add(pojo);

        pojo = new ContactPojo();
        pojo.setFname("Max");
        pojo.setLname("Mustermann");
        pojo.setCountry("Germany");
        pojo.setPhoneNumber("+4312132132132");
        pojo.setEmail("Some@some.com");
        pojo.setPlace("Dornbirn");
        pojo.setPostcode("8505");
        pojo.setStreet("SomeStreet 4");
        contactPojos.add(pojo);
    }

    public static ReservationMock getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationMock();
            }
        }
        return instance;
    }

    private Collection<ContactPojo> contactPojos = new HashSet<>();

    @Override
    public void newContact(long sessionId, ContactPojo contactPojo)
            throws SessionFaultException, ValidationFault, ArgumentFaultException {
        contactPojos.add(contactPojo);
    }

    @Override
    public Collection<ContactPojo> getAllContacts(long sessionId) throws SessionFaultException {
        return new HashSet<>(contactPojos);
    }

    @Override
    public void updateContact(long sessionId, ContactPojo contactPojo)
            throws SessionFaultException, ValidationFault, ArgumentFaultException {
        contactPojos.add(contactPojo);
    }
}
