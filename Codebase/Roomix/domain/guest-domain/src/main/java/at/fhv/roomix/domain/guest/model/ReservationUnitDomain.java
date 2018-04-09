package at.fhv.roomix.domain.guest.model;

import java.sql.Date;

public class ReservationUnitDomain {
    private int reservationUnitId;
    private int reservation;
    private int roomCategory;
    private Integer cancelation;
    private Integer arrangement;
    private Date startDate;
    private Date endDate;

    public int getReservationUnitId() {
        return reservationUnitId;
    }

    public void setReservationUnitId(int reservationUnitId) {
        this.reservationUnitId = reservationUnitId;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getCancelation() {
        return cancelation;
    }

    public void setCancelation(Integer cancelation) {
        this.cancelation = cancelation;
    }

    public Integer getArrangement() {
        return arrangement;
    }

    public void setArrangement(Integer arrangement) {
        this.arrangement = arrangement;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
