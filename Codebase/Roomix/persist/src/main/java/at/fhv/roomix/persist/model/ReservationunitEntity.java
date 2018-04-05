package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "reservationunit", schema = "roomix", catalog = "")
public class ReservationunitEntity {
    private int reservationUnitId;
    private int reservation;
    private int roomCategory;
    private Integer cancelation;
    private Integer arrangement;
    private Date startDate;
    private Date endDate;
    private Collection<InvoicepositionEntity> invoicepositionsByReservationUnitId;
    private ReservationEntity reservationByReservation;
    private RoomcategoryEntity roomcategoryByRoomCategory;
    private CancellationEntity cancellationByCancelation;
    private ArrangementEntity arrangementByArrangement;
    private Collection<RoomassignmentEntity> roomassignmentsByReservationUnitId;

    @Id
    @Column(name = "ReservationUnitID")
    public int getReservationUnitId() {
        return reservationUnitId;
    }

    public void setReservationUnitId(int reservationUnitId) {
        this.reservationUnitId = reservationUnitId;
    }

    @Basic
    @Column(name = "Reservation")
    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    @Basic
    @Column(name = "RoomCategory")
    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Basic
    @Column(name = "Cancelation")
    public Integer getCancelation() {
        return cancelation;
    }

    public void setCancelation(Integer cancelation) {
        this.cancelation = cancelation;
    }

    @Basic
    @Column(name = "Arrangement")
    public Integer getArrangement() {
        return arrangement;
    }

    public void setArrangement(Integer arrangement) {
        this.arrangement = arrangement;
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
        ReservationunitEntity that = (ReservationunitEntity) o;
        return reservationUnitId == that.reservationUnitId &&
                reservation == that.reservation &&
                roomCategory == that.roomCategory &&
                Objects.equals(cancelation, that.cancelation) &&
                Objects.equals(arrangement, that.arrangement) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservationUnitId, reservation, roomCategory, cancelation, arrangement, startDate, endDate);
    }

    @OneToMany(mappedBy = "reservationunitByReservationUnit")
    public Collection<InvoicepositionEntity> getInvoicepositionsByReservationUnitId() {
        return invoicepositionsByReservationUnitId;
    }

    public void setInvoicepositionsByReservationUnitId(Collection<InvoicepositionEntity> invoicepositionsByReservationUnitId) {
        this.invoicepositionsByReservationUnitId = invoicepositionsByReservationUnitId;
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
    public RoomcategoryEntity getRoomcategoryByRoomCategory() {
        return roomcategoryByRoomCategory;
    }

    public void setRoomcategoryByRoomCategory(RoomcategoryEntity roomcategoryByRoomCategory) {
        this.roomcategoryByRoomCategory = roomcategoryByRoomCategory;
    }

    @ManyToOne
    @JoinColumn(name = "Cancelation", referencedColumnName = "CancellationID")
    public CancellationEntity getCancellationByCancelation() {
        return cancellationByCancelation;
    }

    public void setCancellationByCancelation(CancellationEntity cancellationByCancelation) {
        this.cancellationByCancelation = cancellationByCancelation;
    }

    @ManyToOne
    @JoinColumn(name = "Arrangement", referencedColumnName = "ArrangementID")
    public ArrangementEntity getArrangementByArrangement() {
        return arrangementByArrangement;
    }

    public void setArrangementByArrangement(ArrangementEntity arrangementByArrangement) {
        this.arrangementByArrangement = arrangementByArrangement;
    }

    @OneToMany(mappedBy = "reservationunitByReservationUnit")
    public Collection<RoomassignmentEntity> getRoomassignmentsByReservationUnitId() {
        return roomassignmentsByReservationUnitId;
    }

    public void setRoomassignmentsByReservationUnitId(Collection<RoomassignmentEntity> roomassignmentsByReservationUnitId) {
        this.roomassignmentsByReservationUnitId = roomassignmentsByReservationUnitId;
    }
}
