package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.model.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

public class ReservationControllerMock implements IReservationController {
    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException, GetFault {
        Collection<ReservationPojo> testReservations = new LinkedList<>();
        ReservationPojo reservationPojo = new ReservationPojo();
        reservationPojo.setId(1);
        reservationPojo.setReservationStatus("TEST");
        ContactPojo contact = new ContactPojo();
        contact.setFirstName("Moritz");
        contact.setLastName("Wilfling");
        reservationPojo.setContractingParty(contact);

        Collection<ContactPojo> persons = new LinkedList<>();
        ContactPojo tempPerson = new ContactPojo();
        tempPerson.setFirstName("Slim");
        tempPerson.setLastName("Shady");
        persons.add(tempPerson);
        tempPerson = new ContactPojo();
        tempPerson.setFirstName("Method");
        tempPerson.setLastName("Man");
        persons.add(tempPerson);
        reservationPojo.setPersonReservationsByReservationId(persons);

        Collection<ReservationOptionPojo> options = new LinkedList<>();
        ReservationOptionPojo tempOption = new ReservationOptionPojo();
        tempOption.setOptionDueDate(LocalDate.now());
        tempOption.setOptionDescription("yo");
        tempOption.setOptionFee(new PricePojo());
        options.add(tempOption);
        reservationPojo.setReservationOptionByReservationOption(options);

        Collection<ArrangementPojo> arrangementPojos = new LinkedList<>();
        ArrangementPojo arrangementPojo = new ArrangementPojo();
        arrangementPojo.setDescription("irgendwas mit Wellness");
        arrangementPojo.setName("Wellness Quatsch");
        arrangementPojos.add(arrangementPojo);


        Collection<ReservationUnitPojo> units = new LinkedList<>();
        ReservationUnitPojo tempUnit = new ReservationUnitPojo();
        tempUnit.setStartDate(LocalDate.now());
        tempUnit.setEndDate(LocalDate.now());
        RoomCategoryPojo roomCategoryPojo = new RoomCategoryPojo();
        roomCategoryPojo.setDescription("Einzelzimmer");
        tempUnit.setRoomCategory(roomCategoryPojo);
        tempUnit.setArrangements(arrangementPojos);
        units.add(tempUnit);
        reservationPojo.setReservationUnitsByReservationId(units);

        testReservations.add(reservationPojo);
        return testReservations;
    }

    @Override
    public PricePojo calculatePrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException, ArgumentFaultException, GetFault {
        return null;
    }

    @Override
    public Collection<RoomCategoryPojo> getRoomAllocation(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contractingParty) throws SessionFaultException, ArgumentFaultException, GetFault {
        return null;
    }

    @Override
    public Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException, GetFault {
        return null;
    }

    @Override
    public Collection<ArrangementPojo> getAllArrangement(long sessionId) throws SessionFaultException, GetFault {
        return null;
    }

    @Override
    public Collection<PaymentTypePojo> getPaymentTypes(long sessionId) throws SessionFaultException, GetFault {
        return null;
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException, SaveFault {

    }
}
