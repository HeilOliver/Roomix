package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.reservation.Person;

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

    /**
     * Returns all Reservation that are found by the given query
     */
    Collection<ReservationPojo> getSearchedReservation(long sessionId, String query)
            throws SessionFaultException, GetFault;

    /**
     * Returns all Persons that are found by the given query.
     * This includes all Contacts that ContractingParty Type = Individual or None
     * and already created Persons. This means not created Persons will also be included with id = 0
     */
    Collection<PersonPojo> getSearchedPersons(long sessionId, String query)
            throws SessionFaultException, GetFault;

    /**
     *  Calculates the Category data for a given Category and an given Contracting Party.
     *  Contracting party is allowed to be null. If null normal prices are taken.
     *  Returns an Collection with an entry for each day
     */
    Collection<CategoryDataPojo> calculateData(long sessionId, RoomCategoryPojo pojo, ContractingParty party)
            throws SessionFaultException,ValidationFault, ArgumentFaultException, GetFault;

    /**
     * Returns all RoomCategories
     */
    Collection<RoomCategoryPojo> getAllCategory(long sessionId)
            throws SessionFaultException, GetFault;

    /**
     * Returns all Arrangement (and only the Arrangement)
     */
    Collection<ArrangementPojo> getAllArrangement(long sessionId)
            throws SessionFaultException, GetFault;

    /**
     * Returns all Payment Types
     */
    Collection<PaymentTypePojo> getPaymentTypes(long sessionId)
            throws SessionFaultException, GetFault;

    /**
     * Updates/Creates the given Reservation
     */
    void updateReservation(long sessionId, ReservationPojo reservationPojo)
            throws SessionFaultException, ValidationFault, ArgumentFaultException, SaveFault;
}
