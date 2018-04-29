package at.fhv.roomix.domain.guest.model;

import java.sql.Date;
import java.sql.Time;

public class ReservationUnitDomain {

    private int reservationUnitId;
    private int reservation;
    private int roomCategory;
    private Integer cancellation;
    private Time arrivalTime;
    private Date startDate;
    private Date endDate;

    private RoomCategoryDomain roomCategoryByRoomCategory;
    private CancellationDomain cancellationByCancellation;
    private ReservationDomain reservationByReservation;

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

    public Integer getCancellation() {
        return cancellation;
    }

    public void setCancellation(Integer cancellation) {
        this.cancellation = cancellation;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
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


    public RoomCategoryDomain getRoomCategoryByRoomCategory() {
        return roomCategoryByRoomCategory;
    }

    public void setRoomCategoryByRoomCategory(RoomCategoryDomain roomCategoryByRoomCategory) {
        this.roomCategoryByRoomCategory = roomCategoryByRoomCategory;
    }

    public CancellationDomain getCancellationByCancellation() {
        return cancellationByCancellation;
    }

    public void setCancellationByCancellation(CancellationDomain cancellationByCancellation) {
        this.cancellationByCancellation = cancellationByCancellation;
    }

    public ReservationDomain getReservationByReservation() {
        return reservationByReservation;
    }

    public void setReservationByReservation(ReservationDomain reservationByReservation) {
        this.reservationByReservation = reservationByReservation;
    }

}
