package at.fhv.roomix.controller.stay;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.model.CheckInReply;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.controller.model.CheckInPojo;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * IStayController
 * 22.03.2018 OliverH
 * <p>
 * The Interface for the StayController
 */
public interface IStayController {

    /**
     * Check in a given Unit with the given Persons. If the Persons are not created jet they will be created. If
     * they already exist they will be updated.
     */
    CheckInReply setUnitsForCheckIn(long sessionId, CheckInPojo checkInPojo) throws ArgumentFaultException, SessionFaultException, ValidationFault, CheckInException, SaveFault;

    /**
     * Returns all Reservations that are checkIn able and matches with the query.
     */
    Collection<ReservationPojo> getSearchedReservations(long sessionId, String query) throws GetFault, SessionFaultException;
}
