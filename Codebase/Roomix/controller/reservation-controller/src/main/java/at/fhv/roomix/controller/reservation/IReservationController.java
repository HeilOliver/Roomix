package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.model.ContactPojo;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * IReservationController
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public interface IReservationController {

    void newContact(ContactPojo contactPojo);

    Collection<ContactPojo> getAllContacts();

    void updateContact(ContactPojo contactPojo);
}
