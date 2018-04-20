package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ReservationUnit", schema = "Roomix", catalog = "")
public class ReservationUnitEntity {
    private int reservationUnitId;
    private int reservation;
    private int roomCategory;
    private Integer amountOfRooms;
    private Integer cancellation;
    private Time arrivalTime;
    private Date startDate;
    private Date endDate;

    private Collection<InvoicePositionEntity> invoicePositionsByReservationUnitId;
    private Collection<RoomAssignmentEntity> roomAssignmentsByReservationUnitId;

    private ReservationEntity reservationByReservation;
    private RoomCategoryEntity roomCategoryByRoomCategory;
    private CancellationEntity cancellationByCancellation;

    @Id
    @Column(name = "ReservationUnitID")
    public int getReservationUnitId() {
        return reservationUnitId;
    }

    public void setReservationUnitId(int reservationUnitId) {
        this.reservationUnitId = reservationUnitId;
    }

    @Basic
    @Column(name = "Reservation", insertable = false, updatable = false)
    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    @Basic
    @Column(name = "RoomCategory", insertable = false, updatable = false)
    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Basic
    @Column(name = "AmountOfRooms")
    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    @Basic
    @Column(name = "Cancellation", insertable = false, updatable = false)
    public Integer getCancellation() {
        return cancellation;
    }

    public void setCancellation(Integer cancellation) {
        this.cancellation = cancellation;
    }

    @Basic
    @Column(name = "ArrivalTime")
    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "StartDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "EndDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationUnitEntity that = (ReservationUnitEntity) o;
        return reservationUnitId == that.reservationUnitId &&
                reservation == that.reservation &&
                roomCategory == that.roomCategory &&
                Objects.equals(amountOfRooms, that.amountOfRooms) &&
                Objects.equals(cancellation, that.cancellation) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservationUnitId, reservation, roomCategory, amountOfRooms, cancellation, arrivalTime, startDate, endDate);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationUnitByReservationUnit")
    public Collection<InvoicePositionEntity> getInvoicePositionsByReservationUnitId() {
        return invoicePositionsByReservationUnitId;
    }

    public void setInvoicePositionsByReservationUnitId(Collection<InvoicePositionEntity> invoicePositionsByReservationUnitId) {
        this.invoicePositionsByReservationUnitId = invoicePositionsByReservationUnitId;
    }

    @ManyToOne
    @JoinColumn(name = "Reservation", referencedColumnName = "ReservationID", nullable = false)
    public ReservationEntity getReservationByReservation() {
        return reservationByReservation;
    }

    public void setReservationByReservation(ReservationEntity reservationByReservation) {
        this.reservationByReservation = reservationByReservation;
    }

    @ManyToOne
    @JoinColumn(name = "RoomCategory", referencedColumnName = "RoomCategoryID", nullable = false)
    public RoomCategoryEntity getRoomCategoryByRoomCategory() {
        return roomCategoryByRoomCategory;
    }

    public void setRoomCategoryByRoomCategory(RoomCategoryEntity roomCategoryByRoomCategory) {
        this.roomCategoryByRoomCategory = roomCategoryByRoomCategory;
    }

    @ManyToOne
    @JoinColumn(name = "Cancellation", referencedColumnName = "CancellationID")
    public CancellationEntity getCancellationByCancellation() {
        return cancellationByCancellation;
    }

    public void setCancellationByCancellation(CancellationEntity cancellationByCancellation) {
        this.cancellationByCancellation = cancellationByCancellation;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationUnitByReservationUnit")
    public Collection<RoomAssignmentEntity> getRoomAssignmentsByReservationUnitId() {
        return roomAssignmentsByReservationUnitId;
    }

    public void setRoomAssignmentsByReservationUnitId(Collection<RoomAssignmentEntity> roomAssignmentsByReservationUnitId) {
        this.roomAssignmentsByReservationUnitId = roomAssignmentsByReservationUnitId;
    }
}
