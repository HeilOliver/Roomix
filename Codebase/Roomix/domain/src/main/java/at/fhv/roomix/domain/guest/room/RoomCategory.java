package at.fhv.roomix.domain.guest.room;

import java.util.Objects;

/**
 * Roomix
 * at.fhv.roomix.domain.room
 * RoomCategory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomCategory {
    private int id;
    private String description;
    private int rooms;

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

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCategory that = (RoomCategory) o;
        return id == that.id &&
                rooms == that.rooms &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, rooms);
    }
}
