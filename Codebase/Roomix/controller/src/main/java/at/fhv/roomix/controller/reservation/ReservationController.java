package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.common.validator.Validator;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.payment.DayPrice;
import at.fhv.roomix.domain.payment.PriceCalculator;
import at.fhv.roomix.domain.reservation.*;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.domain.stay.CategoryStatus;
import at.fhv.roomix.persist.builder.accessbuilder.*;
import at.fhv.roomix.persist.builder.dependencybuilder.CategoryFinderBuilder;
import at.fhv.roomix.persist.builder.dependencybuilder.PriceCalculatorBuilder;
import at.fhv.roomix.persist.dataaccess.dao.PersonDao;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.dataaccess.factory.PersonFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;

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
    private static final ModelMapper mapper = new ModelMapper();
    private final ISessionDomain sessionHandler = SessionFactory.getInstance();

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
            for (Reservation reservation:resultSet) {
                ReservationPojo reservationPojo = new ReservationPojo();
                CommentPojo commentPojo = new CommentPojo();
                commentPojo.setComment(reservation.getComment());
                reservationPojo.setComment(commentPojo);
                reservationPojo.setContractingParty(mapper.map(reservation.getContractingParty().getContact(), ContactPojo.class));
                reservationPojo.setPaymentType(reservation.getPaymentType().getId());
                reservationPojo.setReservationStatus(reservation.getStatus().toString());
                Set<ReservationOption> reservationOptionSet = new HashSet<>();
                Set<ReservationOptionPojo> reservationOptionPojoSet = new HashSet<>();
                reservationOptionSet.add(reservation.getOption());
                for (ReservationOption reservationOption:reservationOptionSet) {
                    reservationOptionPojoSet.add(mapper.map(reservationOption,ReservationOptionPojo.class));
                }
                reservationPojo.setReservationOptionByReservationOption(reservationOptionPojoSet);
                Set<ContactPojo> contactPojoSet = new HashSet<>();
                Set<Person> personSet = new HashSet<>(reservation.getGuests());
                for (Person person: personSet) {
                    contactPojoSet.add(mapper.map(person.getContact(),ContactPojo.class));
                }
                reservationPojo.setPersonReservationsByReservationId(contactPojoSet);

                reservationPojoSet.add(reservationPojo);
            }
            //Set<ReservationPojo> collect = resultSet.stream().map((res) -> mapper.map(res, ReservationPojo.class)).collect(Collectors.toSet());
            return reservationPojoSet;
        } catch (BuilderLoadException | MappingException e) {
            throw new GetFault("Exception by loading data, see inner exception fore more details", e);
        }
    }

    @Override
    public PricePojo calculatePrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contactPojo)
            throws SessionFaultException, ArgumentFaultException, GetFault {
        if (reservationUnit == null) throw new ArgumentFaultException("ReservationUnitPojo is not allowed to be null");
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            ContractingParty party = null;
            if (contactPojo != null && contactPojo.getContactId() > 0)
                party = ContractingPartyBuilder.get(contactPojo.getContactId());

            PriceCalculator calculator = PriceCalculatorBuilder.get(party);

            //TODO Map here to DomainObj
            ReservationUnit unit = mapper.map(reservationUnit, ReservationUnit.class);
            Collection<DayPrice> priceFor = calculator.getPriceFor(unit);

            // TODO Map here to POJO
            // var a = mapper.map(priceFor, something.class)
            return null;
        } catch (BuilderLoadException | MappingException e) {
            throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
        }
    }

    @Override
    public Collection<RoomCategoryPojo> getRoomAllocation(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contactPojo) throws SessionFaultException, ArgumentFaultException, GetFault {
        if (startDate == null) throw new ArgumentFaultException("Start Date is not allowed to be null");
        if (endDate == null) throw new ArgumentFaultException("End Date is not allowed to be null");
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            ContractingParty party = null;
            if (contactPojo != null && contactPojo.getContactId() > 0)
                party = ContractingPartyBuilder.get(contactPojo.getContactId());

            Collection<RoomCategory> categories = RoomCategoryBuilder.getRoomCategories();

            Map<RoomCategory, List<CategoryStatus>> result = new HashMap<>();
            for (RoomCategory category : categories) {
                List<CategoryStatus> categoryStatus = CategoryFinderBuilder.create().calculateStatus(startDate, endDate, category, party);
                result.put(category, categoryStatus);
            }

            // TODO Map here to POJO
            Set<RoomCategoryPojo> roomCategoryPojoSet = new HashSet<>();
            for (RoomCategory roomCategory:result.keySet()) {
                RoomCategoryPojo roomCategoryPojo = new RoomCategoryPojo();
                roomCategoryPojo.setDescription(roomCategory.getDescription());
                // Todo fehlende Attribute
            }
            // mapper.map(result, something.class)
            return null;
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
            Reservation reservation = new Reservation();
            reservation.setComment(reservationPojo.getComment().getComment());
            ContractingParty contractingParty = ContractingPartyBuilder.get(reservationPojo.getContractingParty().getContactId());
            reservation.setContractingParty(contractingParty);
            Reservation.Status status = Reservation.Status.valueOf(reservationPojo.getReservationStatus());
            reservation.setStatus(status);

            Set<Person> personSet = new HashSet<>();
            Person person = new Person();
            ContactPojo contactPojo = new ContactPojo();
            // Todo Wie bekomme ich aus einem Kontakt die Person?
            reservation.setGuests(null);
            //Todo Oli du bekommst eine scheiß Collection und nicht eine einzelne Option!
            reservation.setOption(null);

            Set<ReservationUnit> reservationUnitSet = new HashSet<>();
            Set<ReservationUnitPojo> reservationUnitPojoSet = new HashSet<>(reservationPojo.getReservationUnitsByReservationId());
            for (ReservationUnitPojo reservationUnitPojo: reservationUnitPojoSet) {
                reservationUnitSet.add(mapper.map(reservationUnitPojo,ReservationUnit.class));
            }
            reservation.setUnits(reservationUnitSet);

            //Todo Payment ist jetzt komplett anders und stimmt somit mit der Gui nicht mehr überein!
            reservation.setPaymentType(null);


            ReservationBuilder.update(reservation);

            EntityFactory.commitAll();
        } catch (MappingException | PersistException e) {
            throw new SaveFault(e.getMessage(), e);
        } finally {
            EntityFactory.stashChanges();
        }
    }
}
