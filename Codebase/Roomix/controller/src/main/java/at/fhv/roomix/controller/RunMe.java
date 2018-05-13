package at.fhv.roomix.controller;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.controller.common.exceptions.CheckInException;
import at.fhv.roomix.controller.session.SessionControllerFactory;
import at.fhv.roomix.controller.stay.IStayController;
import at.fhv.roomix.controller.stay.StayControllerFactory;
import at.fhv.roomix.domain.session.InvalidUserPasswordCombination;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.domain.session.model.RoomixSession;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller
 * RunMe
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RunMe {
    public static void main(String[] args) throws InvalidUserPasswordCombination, GetFault, SessionFaultException, CheckInException, ArgumentFaultException, ValidationFault, SaveFault {
        RoomixSession session = SessionFactory.getInstance().getSession("Hallo", "0000");
        IStayController stayController = StayControllerFactory.getInstance();

        Collection<ReservationPojo> pojos = stayController.getSearchedReservations(session.getSessionId(), " ");
        ReservationPojo pojo = pojos.stream().findFirst().get();

        Collection<PersonPojo> persons = pojo.getPersons();
        ReservationUnitPojo unitPojo = pojo.getUnits().stream().findFirst().get();

        PersonPojo personPojo = persons.stream().findFirst().get();
        personPojo.setForeName("Test");

        CheckInPojo checkInPojo = new CheckInPojo();
        checkInPojo.setUnit(unitPojo);
        checkInPojo.setAssignedPerson(persons);

        CheckInReply checkInReply = stayController.setUnitsForCheckIn(session.getSessionId(), checkInPojo);

        SessionControllerFactory.getInstance().dispose();
    }
}
