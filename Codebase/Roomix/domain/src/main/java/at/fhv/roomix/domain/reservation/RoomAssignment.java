package at.fhv.roomix.domain.reservation;

import at.fhv.roomix.domain.room.Room;

import javax.naming.NamingException;
import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomAssignment {
    private int id;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Room assignedRoom;
    private ReservationUnit unit;

    public ReservationUnit getUnit() {
        return unit;
    }

    public Room getAssignedRoom() {
        return assignedRoom;
    }

    public int getId() {
        return id;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setAssignedRoom(Room assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    public void setUnit(ReservationUnit unit) {
        this.unit = unit;
    }
}
