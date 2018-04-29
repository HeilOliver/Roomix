package at.fhv.roomix.domain.guest.model;

import java.sql.Timestamp;

public class InvoicePositionDomain {
    private int invoicePositionId;
    private int reservation;
    private int reservationUnit;
    private Integer invoice;
    private Integer roomAssignment;
    private Integer article;
    private String comment;
    private int price;
    private int paidFlag;
    private Timestamp determinationDate;
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

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String freePosition) {
        this.comment = freePosition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int amount) {
        this.price = amount;
    }

    public Timestamp getDeterminationDate() {
        return determinationDate;
    }

    public void setDeterminationDate(Timestamp determinationDate) {
        this.determinationDate = determinationDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPaidFlag() {
        return paidFlag;
    }

    public void setPaidFlag(int paidFlag) {
        this.paidFlag = paidFlag;
    }
}
