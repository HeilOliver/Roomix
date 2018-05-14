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
import at.fhv.roomix.persist.builder.dependencybuilder.CategoryFinderBuilder;
import at.fhv.roomix.persist.builder.dependencybuilder.PriceCalculatorBuilder;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

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
    private static final Mapper _mapper = Mapper.getInstance();
    private final ISessionDomain sessionHandler = SessionFactory.getInstance();

    static {
        mapper.addMappings(new PropertyMap<ReservationOption, ReservationOptionPojo>() {
            @Override
            protected void configure() {
                skip().setOptionStatus((byte) 0);
            }
        });
        _mapper.addMapType(new ReservationMapping(), Reservation.class, ReservationPojo.class);
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
                reservationPojoSet.add(_mapper.map(reservation, ReservationPojo.class));
            }
            return reservationPojoSet;
        } catch (BuilderLoadException | MappingException | IllegalStateException e) {
            throw new GetFault("Exception by loading data, see inner exception fore more details", e);
        }
    }

    @Override
    public Collection<PersonPojo> getSearchedPersons(long sessionId, String query) throws SessionFaultException, GetFault {
        return null;
    }

    @Override
    public Collection<CategoryDataPojo> calculateData(long sessionId, RoomCategoryPojo pojo, ContractingParty party)
            throws SessionFaultException,ValidationFault, ArgumentFaultException, GetFault {
//        if (reservationUnit == null) throw new ArgumentFaultException("ReservationUnitPojo is not allowed to be null");
//        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
//
//        try {
//            ContractingParty party = null;
//            if (contactPojo != null && contactPojo.getContactId() > 0)
//                party = ContractingPartyBuilder.get(contactPojo.getContactId());
//
//            PriceCalculator calculator = PriceCalculatorBuilder.get(party);
//
//            //TODO Map here to DomainObj
//            ReservationUnit unit = mapper.map(reservationUnit, ReservationUnit.class);
//            Collection<DayPrice> priceFor = calculator.getPriceFor(unit);
//
//            // TODO Map here to POJO
//            // var a = mapper.map(priceFor, something.class)
//            return null;
//        } catch (BuilderLoadException | MappingException e) {
//            throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
//        }
        throw new GetFault();
    }

    /*@Override
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
    }*/

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


            ReservationBuilder.update(toUpdate);
            EntityFactory.commitAll();
        } catch (MappingException | PersistException e) {
            throw new SaveFault(e.getMessage(), e);
        } finally {
            EntityFactory.stashChanges();
        }
    }
}
