package at.fhv.roomix.domain.guest.model;

import java.sql.Date;

public class RoomAssignmentDomain {
    private int roomAssignmentId;
    private Date arrivalDate;
    private Date departureDate;
    private int room;
    private int reservationUnit;
    private RoomDomain roomByRoom;
    private ReservationUnitDomain reservationUnitByReservationUnit;

    public int getRoomAssignmentId() {
        return roomAssignmentId;
    }

    public void setRoomAssignmentId(int roomAssignmentId) {
        this.roomAssignmentId = roomAssignmentId;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(int reservationUnit) {
        this.reservationUnit = reservationUnit;
    }

    public RoomDomain getRoomByRoom() {
        return roomByRoom;
    }

    public void setRoomByRoom(RoomDomain roomByRoom) {
        this.roomByRoom = roomByRoom;
    }

    public ReservationUnitDomain getReservationUnitByReservationUnit() {
        return reservationUnitByReservationUnit;
    }

    public void setReservationUnitByReservationUnit(ReservationUnitDomain reservationUnitByReservationUnit) {
        this.reservationUnitByReservationUnit = reservationUnitByReservationUnit;
    }
}
