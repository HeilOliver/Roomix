package at.fhv.roomix.persist.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ReservationUnit", schema = "Roomix", catalog = "")
public class ReservationUnitEntity {
    private int reservationUnitId;
    private LocalTime arrivalTime;
    private LocalDate startDate;
    private LocalDate endDate;

    private Collection<InvoicePositionEntity> invoicePositions;
    private Collection<RoomAssignmentEntity> roomAssignments;

    private ReservationEntity reservation;
    private RoomCategoryEntity roomCategory;
    private CancellationEntity cancellation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationUnitID")
    public int getReservationUnitId() {
        return reservationUnitId;
    }

    public void setReservationUnitId(int reservationUnitId) {
        this.reservationUnitId = reservationUnitId;
    }

    @Basic
    @Column(name = "ArrivalTime")
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "StartDate")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "EndDate")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationUnitEntity that = (ReservationUnitEntity) o;
        return reservationUnitId == that.reservationUnitId &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservationUnitId, arrivalTime, startDate, endDate);
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationUnit")
    public Collection<InvoicePositionEntity> getInvoicePositions() {
        return invoicePositions;
    }

    public void setInvoicePositions(Collection<InvoicePositionEntity> invoicePositionsByReservationUnitId) {
        this.invoicePositions = invoicePositionsByReservationUnitId;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationUnit")
    public Collection<RoomAssignmentEntity> getRoomAssignments() {
        return roomAssignments;
    }

    public void setRoomAssignments(Collection<RoomAssignmentEntity> roomAssignmentsByReservationUnitId) {
        this.roomAssignments = roomAssignmentsByReservationUnitId;
    }

    @ManyToOne
    @JoinColumn(name = "Reservation")
    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservationByReservation) {
        this.reservation = reservationByReservation;
    }

    @ManyToOne
    @JoinColumn(name = "RoomCategory")
    public RoomCategoryEntity getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryEntity roomCategoryByRoomCategory) {
        this.roomCategory = roomCategoryByRoomCategory;
    }

    @ManyToOne
    @JoinColumn(name = "Cancellation")
    public CancellationEntity getCancellation() {
        return cancellation;
    }

    public void setCancellation(CancellationEntity cancellationByCancellation) {
        this.cancellation = cancellationByCancellation;
    }


}
