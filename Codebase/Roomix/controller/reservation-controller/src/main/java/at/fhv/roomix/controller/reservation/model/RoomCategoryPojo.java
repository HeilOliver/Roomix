package at.fhv.roomix.controller.reservation.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * RoomCategoryPojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

public class RoomCategoryPojo {

    private int id;

    @NotNull(message = "description cannot be null")
    @Size(min = 1, max = 200, message = "Description must be between 1 and 200 characters")
    private String description;
    private int occupied;
    private int unconfirmedReservation;
    private int free;
    private int confirmedReservation;
    private int quota;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public int getUnconfirmedReservation() {
        return unconfirmedReservation;
    }

    public void setUnconfirmedReservation(int unconfirmedReservation) {
        this.unconfirmedReservation = unconfirmedReservation;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getConfirmedReservation() {
        return confirmedReservation;
    }

    public void setConfirmedReservation(int confirmedReservation) {
        this.confirmedReservation = confirmedReservation;
    }
}
