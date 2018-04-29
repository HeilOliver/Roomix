package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

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
    public Collection<RoomCategoryPojo> getSearchedCategory(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contractingParty) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ArrangementPojo> getAllArrangement(long sessionId) throws SessionFaultException {
        return new HashSet<>();
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

    }
}
