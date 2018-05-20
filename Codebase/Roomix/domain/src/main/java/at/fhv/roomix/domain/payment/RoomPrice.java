package at.fhv.roomix.domain.payment;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.domain.price
 * RoomPrice
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomPrice {
    private int listPrice;
    private int acquisitionPrice;
    private int minimumPrice;
    private int dayPrice;

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
