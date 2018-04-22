package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException;

    Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException;

    Collection<PricePojo> getPrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException;

    Collection<RoomCategoryPojo> getSearchedCategory(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contractingParty) throws SessionFaultException;

    Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException;

    Collection<ArrangementPojo> getAllArrangement(long sessionId) throws SessionFaultException;

    void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException;
}
