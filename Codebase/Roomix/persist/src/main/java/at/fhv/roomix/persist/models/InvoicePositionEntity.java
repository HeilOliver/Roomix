package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "InvoicePosition", schema = "Roomix", catalog = "")
public class InvoicePositionEntity {
    private int invoicePositionId;
    private String comment;
    private int price;
    private int paidFlag;
    private int count;

    private ReservationEntity reservation;
    private ReservationUnitEntity reservationUnit;
    private InvoiceEntity invoice;
    private RoomAssignmentEntity roomAssignment;
    private ArticleEntity article;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoicePositionID")
    public int getInvoicePositionId() {
        return invoicePositionId;
    }

    public void setInvoicePositionId(int invoicePositionId) {
        this.invoicePositionId = invoicePositionId;
    }

    @Basic
    @Column(name = "Comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "Price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "PaidFlag")
    public int getPaidFlag() {
        return paidFlag;
    }

    public void setPaidFlag(int paidFlag) {
        this.paidFlag = paidFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoicePositionEntity that = (InvoicePositionEntity) o;
        return invoicePositionId == that.invoicePositionId &&
                reservation == that.reservation &&
                reservationUnit == that.reservationUnit &&
                price == that.price &&
                count == that.count &&
                Objects.equals(invoice, that.invoice) &&
                Objects.equals(roomAssignment, that.roomAssignment) &&
                Objects.equals(article, that.article) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(invoicePositionId, reservation, reservationUnit, invoice, roomAssignment, article, comment, price, count);
    }

    @ManyToOne
    @JoinColumn(name = "Reservation")
    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservationEntity) {
        this.reservation = reservationEntity;
    }

    @ManyToOne
    @JoinColumn(name = "ReservationUnit")
    public ReservationUnitEntity getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(ReservationUnitEntity reservationUnitEntity) {
        this.reservationUnit = reservationUnitEntity;
    }

    @ManyToOne
    @JoinColumn(name = "Invoice")
    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoiceEntity) {
        this.invoice = invoiceEntity;
    }

    @ManyToOne
    @JoinColumn(name = "RoomAssignment")
    public RoomAssignmentEntity getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(RoomAssignmentEntity roomAssignmentEntity) {
        this.roomAssignment = roomAssignmentEntity;
    }

    @ManyToOne
    @JoinColumn(name = "Article")
    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity articleByArticle) {
        this.article = articleByArticle;
    }
}
