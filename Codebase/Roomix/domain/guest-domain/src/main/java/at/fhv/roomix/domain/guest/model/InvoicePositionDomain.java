package at.fhv.roomix.domain.guest.model;

import java.sql.Timestamp;

public class InvoicePositionDomain {
    private int invoicePositionId;
    private int reservation;
    private int reservationUnit;
    private Integer invoice;
    private Integer roomAssignment;
    private Integer hotelService;
    private int amount;
    private Timestamp dateOfIssue;
    private int count;

    public int getInvoicePositionId() {
        return invoicePositionId;
    }

    public void setInvoicePositionId(int invoicePositionId) {
        this.invoicePositionId = invoicePositionId;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(int reservationUnit) {
        this.reservationUnit = reservationUnit;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Integer getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(Integer roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    public Integer getHotelService() {
        return hotelService;
    }

    public void setHotelService(Integer hotelService) {
        this.hotelService = hotelService;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
