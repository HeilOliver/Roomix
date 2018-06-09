package at.fhv.roomix.controller.implement.adapter;

import at.fhv.roomix.domain.implement.IRoom;
import at.fhv.roomix.domain.room.Room;

import java.util.Observer;

/**
 * Roomix
 * at.fhv.roomix.implement.implement.adapter
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomAdapter implements IRoom {

    private Room room;

    public RoomAdapter(Room room) {
        if (room == null) throw new IllegalArgumentException();
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public int getRoomID() {
        return room.getId();
    }

    @Override
    public String getRoomCategoryDescription() {
        return room.getCategory().getDescription();
    }

    @Override
    public String getStatus() {
        return room.getRoomStatus().toString();
    }

    @Override
    public void addObserver(Observer o) {

    }
}
