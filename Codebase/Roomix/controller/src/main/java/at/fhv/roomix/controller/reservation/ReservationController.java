package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.common.validator.Validator;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.controller.mapping.ReservationMapping;
import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.payment.DayPrice;
import at.fhv.roomix.domain.payment.PriceCalculator;
import at.fhv.roomix.domain.reservation.*;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.domain.stay.CategoryStatus;
import at.fhv.roomix.persist.builder.accessbuilder.*;
import at.fhv.roomix.persist.builder.dependencybuilder.PriceCalculatorBuilder;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistException;
import org.modelmapper.MappingException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * ReservationController
 * 18.04.2018 Robert S.
 * <p>
 * The Implementation for the ReservationController itself
 */
class ReservationController implements IReservationController {
    private static final Mapper mapper = Mapper.getInstance();
    private final ISessionDomain sessionHandler = SessionFactory.getInstance();

    static {
        mapper.addMapType(new ReservationMapping(), Reservation.class, ReservationPojo.class);
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException, GetFault {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        try {
            String[] split = query.toLowerCase().split(" ");

            Collection<Reservation> reservations = ReservationBuilder.getAll();
            Set<Reservation> resultSet = new HashSet<>();
            if (query.trim().isEmpty())
                resultSet.addAll(reservations);

            for (String splicedQuery : split) {
                if (splicedQuery.isEmpty()) continue;
                resultSet.addAll(reservations.stream()
                        .filter((r) -> Integer.toString(r.getId()).contains(splicedQuery) ||
                                r.getGuests().stream().anyMatch(
                                        (g) -> g.getFirstName().contains(splicedQuery) ||
                                                g.getLastName().contains(splicedQuery) ||
                                                Integer.toString(g.getId()).contains(splicedQuery)) ||
                                r.getContractingParty().getContact().getFirstName().contains(splicedQuery) ||
                                r.getContractingParty().getContact().getLastName().contains(splicedQuery) ||
                                Integer.toString(r.getContractingParty().getContact().getId()).contains(splicedQuery))
                        .collect(Collectors.toSet()));
            }

            Set<ReservationPojo> reservationPojoSet = new HashSet<>();

            for (Reservation reservation : resultSet) {
                reservationPojoSet.add(mapper.map(reservation, ReservationPojo.class));
            }
            return reservationPojoSet;
        } catch (BuilderLoadException | MappingException | IllegalStateException e) {
            throw new GetFault("Exception by loading data, see inner exception fore more details", e);
        }
    }

    @Override
    public Collection<PersonPojo> getSearchedPersons(long sessionId, String query) throws SessionFaultException, GetFault {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        try {
            String[] split = query.toLowerCase().split(" ");

            Collection<Person> persons = PersonBuilder.getAll();
            Set<Person> resultSet = new HashSet<>();
            if (query.trim().isEmpty())
                resultSet.addAll(persons);

            for (String splicedQuery : split) {
                if (splicedQuery.isEmpty()) continue;
                resultSet.addAll(persons.stream()
                        .filter((p) -> Integer.toString(p.getId()).contains(splicedQuery) ||
                            p.getFirstName().contains(splicedQuery) ||
                                p.getLastName().contains(splicedQuery))
                        .collect(Collectors.toSet()));
            }

            Set<PersonPojo> pojos = new HashSet<>();

            for (Person person : resultSet) {
                pojos.add(mapper.map(person, PersonPojo.class));
            }
            return pojos;
        } catch (BuilderLoadException | MappingException | IllegalStateException e) {
            throw new GetFault("Exception by loading data, see inner exception fore more details", e);
        }
    }

    @Override
    public Collection<CategoryDataPojo> calculateData(long sessionId, RoomCategoryPojo pojo, ContactPojo party, LocalDate startDate, LocalDate endDate)
            throws SessionFaultException,ValidationFault, ArgumentFaultException, GetFault {
        if (pojo == null) throw new ArgumentFaultException("RoomCategoryPojo is not allowed to be null");
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            ContractingParty contractingParty = null;

            if (party != null && party.getContactId() > 0)
                contractingParty = ContractingPartyBuilder.get(party.getContactId());

            RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(pojo.getId());
            LocalDate currDate = startDate;
            HashSet<CategoryStatus> categoryStatus = new HashSet<>();
            do {
                CategoryStatus status = roomCategory.calculateStatus(contractingParty, currDate);
                categoryStatus.add(status);
                currDate = currDate.plusDays(1);
            } while (currDate.isBefore(endDate));

            return categoryStatus.stream()
                    .map((s)->mapper.map(s,CategoryDataPojo.class))
                    .collect(Collectors.toSet());
        } catch (BuilderLoadException | MappingException e) {
            throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
        }
    }

    public PricePojo calculatePrice(long sessionId, ReservationUnitPojo unit, ContactPojo contractingParty)
            throws SessionFaultException,ValidationFault, ArgumentFaultException, GetFault{
        if (unit == null || contractingParty == null)
            throw new ArgumentFaultException("ReservationUnitPojo or ContactPojo is not allowed to be null");
        if (!sessionHandler.isValidFor(sessionId, null))
            throw new SessionFaultException();

        try {
            ContractingParty party = ContractingPartyBuilder.get(contractingParty.getContactId());
            RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(unit.getRoomCategory().getId());

            LocalDate currDate = unit.getStartDate();
            int price = 0;
            do {
                price += roomCategory.calculatePrice(party, currDate);
            } while (currDate.isBefore(unit.getEndDate()));

            PricePojo pricePojo = new PricePojo();
            pricePojo.setPrice(price);
            return pricePojo;
        } catch (BuilderLoadException | MappingException e) {
            throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
        }
    }

    @Override
    public Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException, GetFault {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            return RoomCategoryBuilder.getRoomCategories()
                    .stream()
                    .map((e) -> mapper.map(e, RoomCategoryPojo.class))
                    .collect(Collectors.toSet());
        } catch (BuilderLoadException e) {
            throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
        }
    }

    @Override
    public Collection<ArrangementPojo> getAllArrangement(long sessionId) throws SessionFaultException, GetFault {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            return ArrangementBuilder.getArrangements()
                    .stream()
                    .map((e) -> mapper.map(e, ArrangementPojo.class))
                    .collect(Collectors.toSet());
        } catch (BuilderLoadException e) {
            throw new GetFault("Cant load Arrangements, see inner exception fore more details", e);
        }
    }

    @Override
    public Collection<PaymentTypePojo> getPaymentTypes(long sessionId) throws SessionFaultException, GetFault {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            return PaymentTypeBuilder.getPaymentTypes()
                    .stream()
                    .map((e) -> mapper.map(e, PaymentTypePojo.class))
                    .collect(Collectors.toSet());
        } catch (BuilderLoadException e) {
            throw new GetFault("Cant load PaymentTypes, see inner exception fore more details", e);
        }
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException, SaveFault {
        if (reservationPojo == null) throw new ArgumentFaultException();
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        Validator.validate(reservationPojo);

        try {
            Reservation toUpdate;
            // If Reservation already exist - Load again here
            if (reservationPojo.getId() > 0) {
                toUpdate = ReservationBuilder.get(reservationPojo.getId());
            } else {
                toUpdate = new Reservation();
            }

            // If Commend exists
            if (reservationPojo.getReservationComment() != null) {
                toUpdate.setComment(reservationPojo.getReservationComment());
            } else {
                toUpdate.setComment(null);
            }

            // If ContractingParty is new
            ContractingParty contractingParty
                    = ContractingPartyBuilder.get(reservationPojo.getContractingParty().getContactId());
            toUpdate.setContractingParty(contractingParty);

            // Map / Update Persons
            Collection<PersonPojo> persons = reservationPojo.getPersons();
            toUpdate.setGuests(new HashSet<>());
            for (PersonPojo person : persons) {
                Person guest = GuestBuilder.getPerson(person.getId());
                mapper.map(person, guest);
                toUpdate.getGuests().add(guest);
            }

            // Add Option
            ReservationOptionPojo option = reservationPojo.getOption();
            if (option != null) {
                ReservationOption reservationOption = ReservationOptionBuilder.get(option.getOptionId());
                mapper.map(option, reservationOption);
                toUpdate.setOption(reservationOption);
            }

            // Add Units
            toUpdate.setUnits(new HashSet<>());
            for (ReservationUnitPojo reservationUnitPojo: reservationPojo.getUnits()) {
                ReservationUnit unit = ReservationUnitBuilder.get(reservationUnitPojo.getId());
                mapper.map(reservationUnitPojo,unit);
                toUpdate.getUnits().add(unit);
            }

            int paymentTypeId = reservationPojo.getPaymentType().getId();
            toUpdate.setPaymentType(PaymentTypeBuilder.getPaymentType(paymentTypeId));

            toUpdate.calculate();

            ReservationBuilder.update(toUpdate);
            EntityFactory.commitAll();
        } catch (MappingException | PersistException e) {
            throw new SaveFault(e.getMessage(), e);
        } finally {
            EntityFactory.stashChanges();
        }
    }
}
