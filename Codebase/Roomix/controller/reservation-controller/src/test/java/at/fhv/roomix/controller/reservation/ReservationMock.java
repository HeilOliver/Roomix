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
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationMock implements IReservationController {

    private static final Object lock = new Object();
    private static ReservationMock instance;
    private Collection<ContactPojo> contactPojos = new HashSet<>();

    public static ReservationMock getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationMock();
            }
        }
        return instance;
    }

    @Override
    public Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<PricePojo> getPrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException {
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
        return null;
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

    }
}
