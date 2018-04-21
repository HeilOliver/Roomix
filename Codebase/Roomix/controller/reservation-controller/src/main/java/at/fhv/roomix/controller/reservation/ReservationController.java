package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;
import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.*;
import at.fhv.roomix.persist.model.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static at.fhv.roomix.controller.common.validator.Validator.validate;


/**
 * Roomix
 * at.fhv.roomix.controller.session
 * ReservationController
 * 18.04.2018 Robert S.
 * <p>
 * The Implementation for the ReservationController itself
 */
class ReservationController implements IReservationController {
    private final ISessionDomain sessionHandler = SessionFactory.getInstance();

    @Override
    public Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<ReservationDomain, ReservationEntity> reservationBuilder = ReservationDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        HashSet<ReservationDomain> reservationDomainSet = new HashSet<>(reservationBuilder.getAll());
        HashSet<ReservationPojo> reservationPojoSet = new HashSet<>();

        reservationDomainSet.forEach(reservation -> reservationPojoSet.add(modelMapper.map(reservation, ReservationPojo.class)));

        return reservationPojoSet;
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        Collection<ReservationPojo> reservationPojoSet = getAllReservation(sessionId);

        String[] split = query.toLowerCase().split(" ");

        Set<ReservationPojo> resultSet = new HashSet<>(reservationPojoSet);
        for (String splitedQuery : split) {
            resultSet = resultSet.stream()
                    .filter(c -> c.getContractingParty().toString().toLowerCase().contains(splitedQuery) ||
                            c.getPersons().toString().toLowerCase().contains(splitedQuery) ||
                            c.getComment().toLowerCase().contains(splitedQuery) ||
                            c.getUnits().toString().toLowerCase().contains(splitedQuery))
                    .collect(Collectors.toSet());
        }

        return resultSet;
    }

    @Override
    public PricePojo getPricebyReservationUnitAndContractingParty(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        
        return null;
    }

    @Override
    public Collection<RoomCategoryPojo> getSearchedCategorybyDateAndContract(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contractingParty) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        IAbstractDomainBuilder<RoomCategoryDomain,RoomCategoryEntity> roomCategoryBuilder = RoomCategoryDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        GuestDomain guestDomain = modelMapper.map(contractingParty, GuestDomain.class);
        HashSet<RoomCategoryDomain> roomCategoryDomainSet = new HashSet<>(roomCategoryBuilder.getAll());
        HashSet<RoomCategoryPojo> roomCategoryset = new HashSet<>();
        for (RoomCategoryDomain roomCategoryDomain : roomCategoryDomainSet) {
            roomCategoryDomain.setCategoryMetaData(startDate,endDate, guestDomain);
            RoomCategoryPojo roomCategoryPojo = new RoomCategoryPojo();
            roomCategoryPojo.setDiscription(roomCategoryDomain.getCategoryDescription());
            roomCategoryPojo.setOccupied(roomCategoryDomain.getMetaData().getNumberOfOccupiedRooms());
            roomCategoryPojo.setUnconfirmedReservation(roomCategoryDomain.getMetaData().getNumberOfConfirmedReservations());
            roomCategoryPojo.setFree(roomCategoryDomain.getMetaData().getNumberOfConfirmedReservations());
            roomCategoryset.add(roomCategoryPojo);
        }

        return roomCategoryset;
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservationbyContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        Collection<ReservationPojo> reservationPojoSet = getAllReservation(sessionId);

        int contactId = contactPojo.getContactId();

        Set<ReservationPojo> resultSet = new HashSet<>(reservationPojoSet);

        for (ReservationPojo resPoj : reservationPojoSet) {
            if (contactId == resPoj.getContractingParty().getContactId()) {
                resultSet.add(resPoj);
            }
        }

        return resultSet;
    }

    @Override
    public Collection<ReservationUnitPojo> getAllReservationUnits(long sessionId) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<ReservationUnitDomain, ReservationUnitEntity> reservationUnitBuilder = ReservationUnitDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        HashSet<ReservationUnitDomain> reservationUnitDomainSet = new HashSet<>(reservationUnitBuilder.getAll());
        HashSet<ReservationUnitPojo> reservationUnitPojoSet = new HashSet<>();

        reservationUnitDomainSet.forEach(reservationUnit -> reservationUnitPojoSet.add(modelMapper.map(reservationUnit, ReservationUnitPojo.class)));

        return reservationUnitPojoSet;
    }

    @Override
    public Collection<ReservationUnitPojo> getSearchedReservationUnit(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        return reservationPojo.getUnits();
    }

    @Override
    public Collection<ReservationOptionPojo> getAllReservationOptions(long sessionId) throws SessionFaultException, ArgumentFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<ReservationOptionDomain, ReservationOptionEntity> reservationOptionBuilder = ReservationOptionDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        HashSet<ReservationOptionDomain> reservationOptionDomainSet = null;
        if (reservationOptionBuilder != null) {
            reservationOptionDomainSet = new HashSet<>(reservationOptionBuilder.getAll());
        } else {
            throw new ArgumentFaultException();
        }
        HashSet<ReservationOptionPojo> reservationOptionPojoSet = new HashSet<>();

        reservationOptionDomainSet.forEach(reservationOption -> reservationOptionPojoSet.add(modelMapper.map(reservationOption, ReservationOptionPojo.class)));

        return reservationOptionPojoSet;
    }

    @Override
    public Collection<ReservationOptionPojo> getSearchedReservationOptions(long sessionId, ReservationUnitPojo reservationUnitPojo) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        return reservationUnitPojo.getOptions();
    }

    @Override
    public Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity> roomCategoryBuilder = RoomCategoryDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        HashSet<RoomCategoryDomain> roomCategoryDomainSet = new HashSet<>(roomCategoryBuilder.getAll());
        HashSet<RoomCategoryPojo> roomCategoryPojoSet = new HashSet<>();

        roomCategoryDomainSet.forEach(roomCategory -> roomCategoryPojoSet.add(modelMapper.map(roomCategory, RoomCategoryPojo.class)));

        return roomCategoryPojoSet;
    }

    @Override
    public void updateReservationOption(long sessionId, ReservationOptionPojo reservationOptionPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

        if (reservationOptionPojo == null) throw new ArgumentFaultException();
        validate(reservationOptionPojo);
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<ReservationOptionDomain, ReservationOptionEntity> reservationOptionBuilder = ReservationOptionDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();

        ReservationOptionDomain reservationOptionDomain = modelMapper.map(reservationOptionPojo, ReservationOptionDomain.class);

        if (reservationOptionBuilder != null) {
            reservationOptionBuilder.set(reservationOptionDomain);
        } else {
            throw new ArgumentFaultException();
        }
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

        if (reservationPojo == null) throw new ArgumentFaultException();
        validate(reservationPojo);
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<ReservationDomain, ReservationEntity> reservationBuilder = ReservationDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();

        ReservationDomain reservationDomain = modelMapper.map(reservationPojo, ReservationDomain.class);

        reservationBuilder.set(reservationDomain);
    }

}
