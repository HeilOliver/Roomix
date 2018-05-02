package at.fhv.roomix.domain.payment;

import at.fhv.roomix.domain.room.RoomCategory;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.domain.payment
 * SeasonPrice
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class SeasonPrice {
    private LocalDate from;
    private LocalDate till;
    private RoomCategory category;

    private int listPrice;
    private int acquisitionPrice;
    private int minimumPrice;
    private int dayPrice;

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTill() {
        return till;
    }

    public void setTill(LocalDate till) {
        this.till = till;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public int getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(int acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(int dayPrice) {
        this.dayPrice = dayPrice;
    }
}
