package at.fhv.roomix.domain.stay;

import at.fhv.roomix.domain.room.RoomCategory;

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
    private final LocalDate date;
    private final RoomCategory category;
    private final int free;
    private final int unconfirmed;
    private final int occupied;
    private final int quota;
    private final int price;

    public CategoryStatus(LocalDate date, RoomCategory category, int unconfirmed, int occupied, int quota, int price) {
        this.date = date;
        this.category = category;
        this.unconfirmed = unconfirmed;
        this.occupied = occupied;
        this.quota = quota;
        this.price = price;
        free = category.getRoomsCount() - unconfirmed - occupied;
    }

    public int getQuota() {
        return quota;
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
