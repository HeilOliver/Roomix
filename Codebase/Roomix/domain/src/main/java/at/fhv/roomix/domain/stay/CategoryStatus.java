package at.fhv.roomix.domain.stay;

import at.fhv.roomix.domain.guest.room.RoomCategory;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.domain.stay
 * CategoryStatus
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryStatus {
    private LocalDate date;
    private RoomCategory category;
    private int free;
    private int unconfirmed;
    private int occupied;

    public CategoryStatus(LocalDate date, RoomCategory category, int unconfirmed, int occupied) {
        this.date = date;
        this.category = category;
        this.unconfirmed = unconfirmed;
        this.occupied = occupied;
        free = category.getRooms() - unconfirmed - occupied;
    }

    public LocalDate getDate() {
        return date;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public int getFree() {
        return free;
    }

    public int getUnconfirmed() {
        return unconfirmed;
    }

    public int getOccupied() {
        return occupied;
    }
}
