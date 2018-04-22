package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationMock
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ReservationControllerMock implements IReservationController {

    @Override
    public Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException {
        return null;
    }

    @Override
    public PricePojo getPrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<RoomCategoryPojo> getSearchedCategorybyDateAndContract(long sessionId, LocalDateTime startDate, LocalDateTime endDate, ContactPojo contractingParty) throws SessionFaultException {
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
