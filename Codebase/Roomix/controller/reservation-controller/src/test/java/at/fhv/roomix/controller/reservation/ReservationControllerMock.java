package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.model.ContactPojo;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationControllerMock
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ReservationControllerMock implements IReservationController {
    @Override
    public void newContact(ContactPojo contactPojo) {

    }

    @Override
    public Collection<ContactPojo> getAllContacts() {
        return null;
    }

    @Override
    public void updateContact(ContactPojo contactPojo) {

    }
}
