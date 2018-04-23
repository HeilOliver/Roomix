package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationMock
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationMock implements IReservationController {

    private static final Object lock = new Object();
    private static ReservationMock instance;
    private Collection<ReservationPojo> reservationPojos = new HashSet<>();
    private Collection<RoomCategoryPojo> roomCategoryPojos = new HashSet<>();
    private PricePojo pricePojo = new PricePojo();
    private Collection<ArrangementPojo> arrangementPojos = new HashSet<>();

    public static ReservationMock getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationMock();
            }
        }
        return instance;
    }

    ReservationMock() {
        ReservationPojo resPojo = new ReservationPojo();
        resPojo.setId(123);
        resPojo.setComment(new CommentPojo());
        resPojo.setContractingParty(new ContactPojo());
        resPojo.setReservationOptionByReservationOption(new HashSet<>());
        resPojo.setPersonReservationsByReservationId(new HashSet<>());
        resPojo.setReservationUnitsByReservationId(new HashSet<>());
        reservationPojos.add(resPojo);

        RoomCategoryPojo roomPojo = new RoomCategoryPojo();
        roomPojo.setId(321);
        roomPojo.setOccupied(3);
        roomPojo.setDescription("roomTest");
        roomPojo.setUnconfirmedReservation(5);
        roomPojo.setFree(10);
        roomPojo.setQuota(15);
        roomPojo.setConfirmedReservation(12);
        roomCategoryPojos.add(roomPojo);

        PricePojo pricePojo = new PricePojo();
        pricePojo.setPrice(420);

        ArrangementPojo arrangementPojo = new ArrangementPojo();
        arrangementPojo.setDescription("arrangementTest");
        DiscountPojo arrDiscount = new DiscountPojo();
        arrDiscount.setDiscount(21);
        arrangementPojo.setDiscount(arrDiscount);
        PricePojo arrPrice = new PricePojo();
        arrPrice.setPrice(12);
        arrangementPojo.setPrice(arrPrice);
        arrangementPojo.setName("arrangementPojo");
        arrangementPojo.setId(5);
        arrangementPojos.add(arrangementPojo);


    }


    @Override
    public Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException {
        return new HashSet<>(reservationPojos);
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException {
        return new HashSet<>(reservationPojos);
    }

    @Override
    public PricePojo getPrice(long sessionId, ReservationUnitPojo reservationUnit, ContactPojo contractingParty) throws SessionFaultException {
        return pricePojo;
    }

    @Override
    public Collection<RoomCategoryPojo> getSearchedCategory(long sessionId, LocalDate startDate, LocalDate endDate, ContactPojo contractingParty) throws SessionFaultException {
        return new HashSet<>(roomCategoryPojos);
    }

    @Override
    public Collection<RoomCategoryPojo> getAllCategory(long sessionId) throws SessionFaultException {
        return new HashSet<>(roomCategoryPojos);
    }

    @Override
    public Collection<ArrangementPojo> getAllArrangement(long sessionId) throws SessionFaultException {
        return new HashSet<>(arrangementPojos);
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {
        reservationPojos.add(reservationPojo);
    }
}
