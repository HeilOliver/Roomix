package at.fhv.roomix.ui.mocks;

import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.exeption.FaultException;
import at.fhv.roomix.controller.reservation.model.ContactPojo;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.ui.mocks
 * ReservationControllerMock
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ReservationControllerMock implements IReservationController {
    private static final Object lock = new Object();
    private static ReservationControllerMock instance;

    private ReservationControllerMock() {
        ContactPojo pojo = new ContactPojo();
        pojo.setForename("Oliver");
        pojo.setSurname("Heil");
        pojo.setCountry("Germany");
        pojo.setPhoneNumber("+4312132132132");
        pojo.setEmail("Some@some.com");
        pojo.setPlace("Dornbirn");
        pojo.setPostcode("8505");
        pojo.setStreet("SomeStreet 4");
        pojo.setActive((byte) 0);
        contactPojos.add(pojo);
    }

    public static ReservationControllerMock getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationControllerMock();
            }
        }
        return instance;
    }

    private Collection<ContactPojo> contactPojos = new HashSet<>();

    @Override
    public void newContact(long sessionId, ContactPojo contactPojo) throws FaultException {
        contactPojos.add(contactPojo);
    }

    @Override
    public Collection<ContactPojo> getAllContacts(long sessionId) throws FaultException {
        return new HashSet<>(contactPojos);
    }

    @Override
    public void updateContact(long sessionId, ContactPojo contactPojo) throws FaultException {
        contactPojos.add(contactPojo);
    }
}
