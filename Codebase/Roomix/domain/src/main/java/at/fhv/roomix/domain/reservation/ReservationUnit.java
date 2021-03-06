package at.fhv.roomix.domain.reservation;

import at.fhv.roomix.domain.common.Proxy;
import at.fhv.roomix.domain.room.RoomCategory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * ReservationUnit
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnit {
    private int id;
    private RoomCategory category;
    private LocalTime arrivalTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private boolean isCanceled;
    private Collection<Arrangement> arrangements = new HashSet<>();
    private Proxy<Reservation> reservation;

    public Collection<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(Collection<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public Reservation getReservation() {
        if (reservation == null) return null;
        return reservation.get();
    }

    public void setReservation(Proxy<Reservation> reservation) {
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }
}
