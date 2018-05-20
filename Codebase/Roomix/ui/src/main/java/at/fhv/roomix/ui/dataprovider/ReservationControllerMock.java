package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;

public class ReservationControllerMock implements IReservationController {

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException, GetFault {
        Collection<ReservationPojo> testReservations = new LinkedList<>();
        ReservationPojo reservationPojo = new ReservationPojo();
        reservationPojo.setId(1);
        ContactPojo contact = new ContactPojo();
        contact.setFirstName("Moritz");
        contact.setLastName("Wilfling");
        reservationPojo.setContractingParty(contact);

        Collection<PersonPojo> persons = new LinkedList<>();
        PersonPojo tempPerson = new PersonPojo();
        tempPerson.setForeName("Slim");
        tempPerson.setLastName("Shady");
        persons.add(tempPerson);
        tempPerson = new PersonPojo();
        tempPerson.setForeName("Method");
        tempPerson.setLastName("Man");
        persons.add(tempPerson);
        reservationPojo.setPersons(persons);

        ReservationOptionPojo tempOption = new ReservationOptionPojo();
        tempOption.setOptionDueDate(LocalDate.now());
        tempOption.setOptionDescription("yo");
        tempOption.setOptionFee(new PricePojo());
        reservationPojo.setOption(tempOption);

        Collection<ArrangementPojo> arrangementPojos = new LinkedList<>();
        ArrangementPojo arrangementPojo = new ArrangementPojo();
        arrangementPojo.setDescription("irgendwas mit Wellness");
        arrangementPojos.add(arrangementPojo);


        Collection<ReservationUnitPojo> units = new LinkedList<>();
        ReservationUnitPojo tempUnit = new ReservationUnitPojo();
        tempUnit.setStartDate(LocalDate.now());
        tempUnit.setEndDate(LocalDate.now());
        tempUnit.setArrivalTime(LocalTime.now());
        RoomCategoryPojo roomCategoryPojo = new RoomCategoryPojo();
        roomCategoryPojo.setDescription("Einzelzimmer");
        tempUnit.setRoomCategory(roomCategoryPojo);
        tempUnit.setArrangements(arrangementPojos);

        units.add(tempUnit);
        reservationPojo.setUnits(units);

        testReservations.add(reservationPojo);
        return testReservations;
    }

    @Override
    public Collection<PersonPojo> getSearchedPersons(long sessionId, String query) throws SessionFaultException, GetFault {
        return null;
    }

    @Override
    public Collection<CategoryDataPojo> calculateData(long sessionId, RoomCategoryPojo pojo, ContactPojo party, LocalDate startDate, LocalDate endDate) throws SessionFaultException, ValidationFault, ArgumentFaultException, GetFault {
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
