package at.fhv.roomix.domain.guest.contractingparty;


import at.fhv.roomix.domain.room.RoomCategory;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.domain.guest.contractingparty
 * Agreement
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Agreement {
    private int id;
    private LocalDate startDate;
    private LocalDate expiringDate;
    private RoomCategory category;
    private int discount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    private int roomCount;

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
