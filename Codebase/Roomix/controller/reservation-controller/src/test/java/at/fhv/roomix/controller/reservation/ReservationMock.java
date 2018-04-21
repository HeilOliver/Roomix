package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.ReservationOptionPojo;
import at.fhv.roomix.controller.reservation.model.ReservationPojo;
import at.fhv.roomix.controller.reservation.model.ReservationUnitPojo;
import at.fhv.roomix.controller.reservation.model.RoomCategoryPojo;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationMock
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationMock implements IReservationController {
    @Override
    public Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservationbyContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationUnitPojo> getAllReservationUnits(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationUnitPojo> getSearchedReservationUnit(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationOptionPojo> getAllReservationOptions(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationOptionPojo> getSearchedReservationOptions(long sessionId, ReservationUnitPojo reservationUnitPojo) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

    }

    @Override
    public void updateReservationOption(long sessionId, ReservationOptionPojo reservationOptionPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

    }
}
