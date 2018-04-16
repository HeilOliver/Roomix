package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.ReservationPojo;

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

    Collection<ContactPojo> getAllContacts(long sessionId) throws SessionFaultException;

    Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException;

    void updateContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException;

    Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException;

    Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException;

    void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException;
}
