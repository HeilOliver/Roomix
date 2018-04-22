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
 * IReservationController
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public interface IReservationController {

    Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException;

    void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException;

    Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException;

    Collection<RoomCategoryPojo> getSearchedCategorybyDateAndContract(long sessionId,LocalDateTime startDate,LocalDateTime endDate, ContactPojo contractingParty) throws SessionFaultException;

    PricePojo getPricebyReservationUnitAndContractingParty(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException;


    Collection<ReservationPojo> getSearchedReservationbyContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException;

    Collection<ReservationUnitPojo> getAllReservationUnits(long sessionId) throws SessionFaultException;

    Collection<ReservationUnitPojo> getSearchedReservationUnit(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException;

    Collection<ReservationOptionPojo> getAllReservationOptions(long sessionId) throws SessionFaultException, ArgumentFaultException;

    Collection<ReservationOptionPojo> getSearchedReservationOptions(long sessionId, ReservationUnitPojo reservationUnitPojo) throws SessionFaultException;

    Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException;

    void updateReservationOption(long sessionId, ReservationOptionPojo reservationOptionPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException;
}
