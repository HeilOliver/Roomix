package at.fhv.roomix.controller.model;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.controller.model
 * CategoryDataPojo
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryDataPojo {
    private LocalDate date;
    private int occupied;
    private int unconfirmedReservation;
    private int free;
    private int confirmedReservation;
    private int quota;
    private int pricePerDay;
    private PricePojo calculatedPrice;

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

    public int getConfirmedReservation() {
        return confirmedReservation;
    }

    public void setConfirmedReservation(int confirmedReservation) {
        this.confirmedReservation = confirmedReservation;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public PricePojo getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(PricePojo calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
