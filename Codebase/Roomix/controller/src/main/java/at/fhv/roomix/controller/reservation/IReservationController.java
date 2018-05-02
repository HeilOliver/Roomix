package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;

import java.time.LocalDate;
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

    Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException, GetFault;

    PricePojo calculatePrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException, ArgumentFaultException, GetFault;

    Collection<RoomCategoryPojo> getRoomAllocation(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contractingParty) throws SessionFaultException, ArgumentFaultException, GetFault;

    Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException, GetFault;

    Collection<ArrangementPojo> getAllArrangement(long sessionId) throws SessionFaultException, GetFault;

    Collection<PaymentTypePojo> getPaymentTypes(long sessionId) throws SessionFaultException, GetFault;

    void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException, SaveFault;
}
