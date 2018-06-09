package at.fhv.roomix.controller.implement.adapter;

import at.fhv.roomix.domain.implement.IReservation;
import at.fhv.roomix.domain.implement.IReservationUnit;
import at.fhv.roomix.domain.implement.IRoom;
import at.fhv.roomix.domain.implement.IRoomCategory;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.room.Room;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Observer;

/**
 * Roomix
 * at.fhv.roomix.implement.implement.adapter
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class ReservationUnitAdapter implements IReservationUnit {

    private final ReservationUnit unit;

    public ReservationUnitAdapter(ReservationUnit unit) {
        if (unit == null) throw new IllegalArgumentException();
        this.unit = unit;
    }

    public ReservationUnit getUnit() {
        return unit;
    }

    @Override
    public int getReservationUnitID() {
        return unit.getId();
    }

    @Override
    public IReservation getReservation() {
        final IReservation reservation = new ReservationAdapter(unit.getReservation());
        return reservation;
    }

    @Override
    public IRoomCategory getRoomCategory() {
        final IRoomCategory category = new RoomCategoryAdapter(unit.getCategory());
        return category;
    }

    @Override
    public Date getStartDate() {
        return Date.from(unit.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Date getEndDate() {
        return Date.from(unit.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String getRoomID() {
        HashMap<LocalDate, Room> rooms = unit.getAssignedRooms();

        if (rooms.containsKey(unit.getStartDate())) {
            return Integer.toString(rooms.get(unit.getStartDate()).getId());
        }
        return "";
    }

    @Override
    public String getRoomStatus() {
        HashMap<LocalDate, Room> rooms = unit.getAssignedRooms();

        if (rooms.containsKey(unit.getStartDate())) {
            return rooms.get(unit.getStartDate()).getRoomStatus().toString();
        }
        return "";
    }

    @Override
    public int getReservationId() {
        return getReservation().getReservationID();
    }

    @Override
    public String getArrivalTime() {
        return unit.getArrivalTime().toString();
    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public IRoom getAssignedRoom() {
        HashMap<LocalDate, Room> rooms = unit.getAssignedRooms();

        if (rooms.containsKey(unit.getStartDate())) {
            return new RoomAdapter(rooms.get(unit.getStartDate()));
        }
        return null;
    }

    @Override
    public void setAssignedRoom(IRoom room) {
        if (!(room instanceof RoomAdapter)) throw new IllegalArgumentException();
        HashMap<LocalDate, Room> rooms = unit.getAssignedRooms();
        rooms.clear();

        LocalDate currDate = unit.getStartDate();
        LocalDate endDate = unit.getEndDate();

        do {
            rooms.put(currDate, ((RoomAdapter) room).getRoom());
            currDate = currDate.plusDays(1);
        } while (currDate.isBefore(endDate));
    }
}
