package at.fhv.roomix.controller.reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ReservationUnitPojo
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnitPojo {
    private int id;
    private int roomCategory;
    private int amount;

    private Collection<ReservationOptionPojo> options;
    private Collection<ArrangementPojo> arrangements;

    private LocalTime arrivalTime;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationUnitPojo() {
        options = new HashSet<>();
        arrangements = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Collection<ReservationOptionPojo> getOptions() {
        return options;
    }

    public void setOptions(Collection<ReservationOptionPojo> options) {
        this.options = options;
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

    public Collection<ArrangementPojo> getArrangements() {
        return arrangements;
    }

    public void setArrangements(Collection<ArrangementPojo> arrangements) {
        this.arrangements = arrangements;
    }
}
