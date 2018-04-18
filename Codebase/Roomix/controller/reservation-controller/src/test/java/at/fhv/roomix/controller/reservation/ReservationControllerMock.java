package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.exeption.SessionFaultException;
import at.fhv.roomix.controller.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ReservationPojo;

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
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

    }
}
