package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;
import at.fhv.roomix.domain.guest.enumtypes.EContractingPartyType;
import at.fhv.roomix.domain.guest.enumtypes.EReservationStatus;
import at.fhv.roomix.domain.guest.enumtypes.ICommonType;
import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.*;
import at.fhv.roomix.persist.model.*;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        reservationDomainSet.forEach(reservation -> {
            ReservationPojo reservationPojo = modelMapper.map(reservation, ReservationPojo.class);
            CommentPojo commentPojo = new CommentPojo();
            commentPojo.setComment(reservation.getReservationComment());
            reservationPojo.setComment(commentPojo);
            reservationPojoSet.add(reservationPojo);
        });
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
                    // TODO master oli, collection in einer collection suchen mit viel for und so?!
                    .filter(c -> c.getContractingParty().toString().toLowerCase().contains(splitedQuery) ||
                            c.getPersonReservationsByReservationId().toString().toLowerCase().contains(splitedQuery) ||
                            c.getComment().toString().toLowerCase().contains(splitedQuery) ||
                            c.getReservationUnitsByReservationId().toString().toLowerCase().contains(splitedQuery))
                    .collect(Collectors.toSet());
        }

        return resultSet;
    }

    @Override
    public PricePojo getPrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        if (contractingParty == null) {
            contractingParty = new ContactPojo();
            contractingParty.setContactId(0);
        }

        RoomCategoryPojo roomCategoryPojo = reservationUnit.getRoomCategory();
        IAbstractDomainBuilder<RoomCategoryDomain,RoomCategoryEntity> roomCategoryBuilder = RoomCategoryDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        GuestDomain guestDomain = modelMapper.map(contractingParty, GuestDomain.class);
        RoomCategoryDomain roomCategoryDomain = roomCategoryBuilder.get(roomCategoryPojo.getId());
        PricePojo pricePojo = new PricePojo();

        roomCategoryDomain.setCategoryMetaData(reservationUnit.getStartDate(),reservationUnit.getEndDate(), guestDomain);
        int price = roomCategoryDomain.getMetaData().getPricePerDay()*(int)Duration.ofDays(ChronoUnit.DAYS.between(reservationUnit.getStartDate(),reservationUnit.getEndDate())).toDays();
        pricePojo.setPrice(price);
        return pricePojo;
    }

    @Override
    public Collection<RoomCategoryPojo> getSearchedCategory(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contractingParty) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        IAbstractDomainBuilder<RoomCategoryDomain,RoomCategoryEntity> roomCategoryBuilder = RoomCategoryDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        //GuestDomain guestDomain = modelMapper.map(contractingParty, GuestDomain.class);
        HashSet<RoomCategoryDomain> roomCategoryDomainSet = new HashSet<>(roomCategoryBuilder.getAll());
        HashSet<RoomCategoryPojo> roomCategoryset = new HashSet<>();

        GuestDomain guestDomain = null;
        if (contractingParty != null && contractingParty.getContactId() > 0) {
            IAbstractDomainBuilder<GuestDomain, ContactEntity> domainBuilder = GuestDomainBuilder.getInstance();
            guestDomain = domainBuilder.get(contractingParty.getContactId());
        }

        for (RoomCategoryDomain roomCategoryDomain : roomCategoryDomainSet) {
            roomCategoryDomain.setCategoryMetaData(startDate,endDate, guestDomain);
            RoomCategoryPojo roomCategoryPojo = new RoomCategoryPojo();
            roomCategoryPojo.setId(roomCategoryDomain.getRoomCategoryId());
            roomCategoryPojo.setDescription(roomCategoryDomain.getCategoryDescription());
            roomCategoryPojo.setPricePerDay(roomCategoryDomain.getMetaData().getPricePerDay());
            roomCategoryPojo.setQuota(roomCategoryDomain.getMetaData().getContingent());
            roomCategoryPojo.setOccupied(roomCategoryDomain.getMetaData().getNumberOfOccupiedRooms());
            roomCategoryPojo.setUnconfirmedReservation(roomCategoryDomain.getMetaData().getNumberOfUnconfirmedReservations());
            roomCategoryPojo.setConfirmedReservation(roomCategoryDomain.getMetaData().getNumberOfConfirmedReservations());
            roomCategoryPojo.setFree(roomCategoryDomain.getMetaData().getTotalNumberOfRooms()-
                                     roomCategoryDomain.getMetaData().getNumberOfConfirmedReservations()-
                                     roomCategoryDomain.getMetaData().getNumberOfOccupiedRooms()-
                                     roomCategoryDomain.getMetaData().getNumberOfUnconfirmedReservations());
            roomCategoryset.add(roomCategoryPojo);
        }
        return roomCategoryset;
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
    public Collection<ArrangementPojo> getAllArrangement(long sessionId) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        Collection<ArrangementPojo> collection = new HashSet<>();
        /* Get all arrangements from article domain builder and filter for article type = arrangement */
        return collection;
    }


    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

        ReservationDomain reservationDomain = new ReservationDomain();

        /* TODO: method name "getPersonReservationsByReservationId" is incorrect */
        Collection<ContactPojo> assignedPersons = reservationPojo.getPersonReservationsByReservationId();
        Collection<PersonDomain> personDomains = new HashSet<>();
        assignedPersons.add(reservationPojo.getContractingParty());
        for (ContactPojo assignedPerson : assignedPersons) {
            int contactID = assignedPerson.getContactId();
            if(contactID > 0) {
                IAbstractDomainBuilder<GuestDomain, ContactEntity> temporaryBuilder = GuestDomainBuilder.getInstance();
                GuestDomain contactOfAssignedPerson = temporaryBuilder.get(contactID);
                Collection<PersonDomain> peopleByContactId = contactOfAssignedPerson.getPeopleByContactId();
                PersonDomain contactToPerson;
                if(peopleByContactId == null || peopleByContactId.isEmpty()){
                    contactToPerson = new PersonDomain();
                    contactToPerson.setFirstName(contactOfAssignedPerson.getFirstName());
                    contactToPerson.setLastName(contactOfAssignedPerson.getLastName());
                    contactToPerson.setContact(contactOfAssignedPerson.getContactId());
                    personDomains.add(contactToPerson);
                } else {
                    contactToPerson = peopleByContactId.iterator().next();
                    PersonDomain flatPerson = new PersonDomain();
                    flatPerson.setFirstName(contactToPerson.getFirstName());
                    flatPerson.setLastName(contactToPerson.getLastName());
                    flatPerson.setContact(contactToPerson.getContact());
                    personDomains.add(flatPerson);
                }
            } else {
                PersonDomain newPerson = new PersonDomain();
                newPerson.setFirstName(assignedPerson.getFirstName());
                newPerson.setLastName(assignedPerson.getLastName());
                personDomains.add(newPerson);
            }
        }


        if (reservationPojo == null) throw new ArgumentFaultException();
        validate(reservationPojo);
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        if(reservationPojo.getContractingParty() == null) throw new ArgumentFaultException();
        ContactPojo contactPojo = reservationPojo.getContractingParty();
        GuestDomain guestDomain = null;
        if(contactPojo.getContactId() > 0) {
            guestDomain = GuestDomainBuilder.getInstance().get(contactPojo.getContactId());
        } else{
            throw new ArgumentFaultException("invalid");
        }
        Collection<ContractingPartyDomain> existingContractingParty = guestDomain.getContractingPartiesByContactId();
        if(existingContractingParty == null || existingContractingParty.isEmpty()){
            ContractingPartyDomain contractingPartyDomain = new ContractingPartyDomain();
            contractingPartyDomain.setContactByContact(guestDomain);
            contractingPartyDomain.setContractingPartyType(EContractingPartyType.INDIVIDUAL.getValue());
            //ContractingPartyDomainBuilder.getInstance().set(contractingPartyDomain);
            reservationDomain.setContractingPartyByContractingParty(contractingPartyDomain);
        } else{
            ContractingPartyDomain actualContractingParty = existingContractingParty.iterator().next();
            reservationDomain.setContractingPartyByContractingParty(actualContractingParty);
        }
        /* TODO: remove hard coded payment type and get it over the reservationPojo  */
        PaymentTypeDomain paymentTypeDomain = PaymentTypeBuilder.getInstance().get(1);
        reservationDomain.setPaymentTypeByPaymentType(paymentTypeDomain);

        Collection<ReservationOptionPojo> reservationOptions = reservationPojo.getReservationOptionByReservationOption();
        if(reservationOptions != null && !reservationOptions.isEmpty()){
            ReservationOptionDomain tempOption =
                    new ModelMapper().map(reservationOptions.iterator().next(), ReservationOptionDomain.class);
            reservationDomain.setReservationOptionByReservationOption(tempOption);
        }
        reservationDomain.setReservationStatus(EReservationStatus.UNCONFIRMED.getValue());
        Collection<ReservationUnitDomain> unitSet = new HashSet<>();
        reservationDomain.setReservationComment(reservationPojo.getComment() == null ? null : reservationPojo.getComment().getComment());
        for (ReservationUnitPojo reservationUnitPojo : reservationPojo.getReservationUnitsByReservationId()) {
            ReservationUnitDomain unit = new ReservationUnitDomain();
            RoomCategoryDomain tempRoomCategory = RoomCategoryDomainBuilder.getInstance().get(reservationUnitPojo.getRoomCategory().getId());
            unit.setRoomCategoryByRoomCategory(tempRoomCategory);
            unit.setStartDate(Date.valueOf(reservationUnitPojo.getStartDate()));
            unit.setEndDate(Date.valueOf(reservationUnitPojo.getEndDate()));
            unitSet.add(unit);
        }
        reservationDomain.setReservationUnitsByReservationId(unitSet);

        Collection<PersonReservationDomain> personReservationDomain = new HashSet<>();
        for (PersonDomain personDomain : personDomains) {
            PersonReservationDomain tempPR = new PersonReservationDomain();
            tempPR.setPersonByPerson(personDomain);
            tempPR.setReservationByReservation(null);
            personReservationDomain.add(tempPR);
        }
        reservationDomain.setPersonReservationsByReservationId(personReservationDomain);

        IAbstractDomainBuilder<ReservationDomain, ReservationEntity> reservationBuilder = ReservationDomainBuilder.getInstance();
        reservationBuilder.set(reservationDomain);
    }
}
