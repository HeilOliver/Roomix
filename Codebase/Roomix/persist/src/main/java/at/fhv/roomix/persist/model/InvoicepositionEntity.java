package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "invoiceposition", schema = "roomix", catalog = "")
public class InvoicepositionEntity {
    private int invoicePositionId;
    private int reservation;
    private int reservationUnit;
    private Integer invoice;
    private Integer roomAssignment;
    private Integer hotelService;
    private int amount;
    private Timestamp dateOfIssue;
    private int count;
    private ReservationEntity reservationByReservation;
    private ReservationunitEntity reservationunitByReservationUnit;
    private InvoiceEntity invoiceByInvoice;

    @Id
    @Column(name = "InvoicePositionID")
    public int getInvoicePositionId() {
        return invoicePositionId;
    }

    public void setInvoicePositionId(int invoicePositionId) {
        this.invoicePositionId = invoicePositionId;
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
    @Column(name = "ReservationUnit")
    public int getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(int reservationUnit) {
        this.reservationUnit = reservationUnit;
    }

    @Basic
    @Column(name = "Invoice")
    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "RoomAssignment")
    public Integer getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(Integer roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    @Basic
    @Column(name = "HotelService")
    public Integer getHotelService() {
        return hotelService;
    }

    public void setHotelService(Integer hotelService) {
        this.hotelService = hotelService;
    }

    @Basic
    @Column(name = "Amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "DateOfIssue")
    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    @Basic
    @Column(name = "Count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoicepositionEntity that = (InvoicepositionEntity) o;
        return invoicePositionId == that.invoicePositionId &&
                reservation == that.reservation &&
                reservationUnit == that.reservationUnit &&
                amount == that.amount &&
                count == that.count &&
                Objects.equals(invoice, that.invoice) &&
                Objects.equals(roomAssignment, that.roomAssignment) &&
                Objects.equals(hotelService, that.hotelService) &&
                Objects.equals(dateOfIssue, that.dateOfIssue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(invoicePositionId, reservation, reservationUnit, invoice, roomAssignment, hotelService, amount, dateOfIssue, count);
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
    @JoinColumn(name = "ReservationUnit", referencedColumnName = "ReservationUnitID", nullable = false)
    public ReservationunitEntity getReservationunitByReservationUnit() {
        return reservationunitByReservationUnit;
    }

    public void setReservationunitByReservationUnit(ReservationunitEntity reservationunitByReservationUnit) {
        this.reservationunitByReservationUnit = reservationunitByReservationUnit;
    }

    @ManyToOne
    @JoinColumn(name = "Invoice", referencedColumnName = "InvoiceID")
    public InvoiceEntity getInvoiceByInvoice() {
        return invoiceByInvoice;
    }

    public void setInvoiceByInvoice(InvoiceEntity invoiceByInvoice) {
        this.invoiceByInvoice = invoiceByInvoice;
    }
}
