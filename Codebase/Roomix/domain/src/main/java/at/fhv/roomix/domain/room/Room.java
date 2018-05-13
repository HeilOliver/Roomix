package at.fhv.roomix.domain.room;

/**
 * Roomix
 * at.fhv.roomix.domain.room
 * Room
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Room {
    private int id;
    private RoomCategory category;
    private RoomStatus roomStatus;

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

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public boolean isDirty() {
        return roomStatus.isDirty;
    }

    public boolean isMultipleOccupied() {
        return roomStatus == RoomStatus.MULTIPLE_OCCUPIED_CLEAN ||
                roomStatus == RoomStatus.MULTIPLE_OCCUPIED_DIRTY;
    }

    public void checkIn() {
        switch (roomStatus) {
            case FREE_CLEAN:
                roomStatus = RoomStatus.SINGLE_OCCUPIED_CLEAN;
                break;
            case OCCUPIED_CLEAN:
            case SINGLE_OCCUPIED_CLEAN:
                roomStatus = RoomStatus.MULTIPLE_OCCUPIED_CLEAN;
                break;
            case FREE_DIRTY:
                roomStatus = RoomStatus.SINGLE_OCCUPIED_DIRTY;
                break;
            case OCCUPIED_DIRTY:
            case SINGLE_OCCUPIED_DIRTY:
                roomStatus = RoomStatus.MULTIPLE_OCCUPIED_DIRTY;
                break;
            case OUT_OF_ORDER:
                throw new IllegalStateException("Room is Out Of Order");
        }
    }

    public enum RoomStatus {
       FREE_CLEAN(false),
        FREE_DIRTY(true),
        OCCUPIED_CLEAN(false),
        OCCUPIED_DIRTY(true),
        SINGLE_OCCUPIED_CLEAN(false),
        SINGLE_OCCUPIED_DIRTY(true),
        MULTIPLE_OCCUPIED_CLEAN(false),
        MULTIPLE_OCCUPIED_DIRTY(true),
        OUT_OF_ORDER(false);

        private boolean isDirty;
        RoomStatus(boolean isDirty) {
            this.isDirty = isDirty;
        }
    }
}
