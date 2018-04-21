package at.fhv.roomix.controller.reservation.model;

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
    private String discription;
    private int occupied;
    private int unconfirmedReservation;
    private int free;
    private int quota;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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
}
