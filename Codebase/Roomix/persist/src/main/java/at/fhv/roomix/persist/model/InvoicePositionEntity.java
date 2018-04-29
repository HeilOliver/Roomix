package at.fhv.roomix.persist.model;

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
    private ReservationEntity reservationByReservation;
    private ReservationUnitEntity reservationUnitByReservationUnit;
    private InvoiceEntity invoiceByInvoice;
    private RoomAssignmentEntity roomAssignmentByRoomAssignment;
    private ArticleEntity articleByArticle;
    private ArrangementEntity arrangementByArrangement;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "InvoicePositionID")
    public int getInvoicePositionId() {
        return invoicePositionId;
    }

    public void setInvoicePositionId(int invoicePositionId) {
        this.invoicePositionId = invoicePositionId;
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
    @Column(name = "ReservationUnit", insertable = false, updatable = false)
    public int getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(int reservationUnit) {
        this.reservationUnit = reservationUnit;
    }

    @Basic
    @Column(name = "Invoice", insertable = false, updatable = false)
    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "RoomAssignment", insertable = false, updatable = false)
    public Integer getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(Integer roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    @Basic
    @Column(name = "Article", insertable = false, updatable = false)
    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
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
    @Column(name = "DeterminationDate")
    public Timestamp getDeterminationDate() {
        return determinationDate;
    }

    public void setDeterminationDate(Timestamp determinationDate) {
        this.determinationDate = determinationDate;
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
                Objects.equals(comment, that.comment) &&
                Objects.equals(determinationDate, that.determinationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(invoicePositionId, reservation, reservationUnit, invoice, roomAssignment, article, comment, price, determinationDate, count);
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
    public ReservationUnitEntity getReservationUnitByReservationUnit() {
        return reservationUnitByReservationUnit;
    }

    public void setReservationUnitByReservationUnit(ReservationUnitEntity reservationUnitByReservationUnit) {
        this.reservationUnitByReservationUnit = reservationUnitByReservationUnit;
    }

    @ManyToOne
    @JoinColumn(name = "Invoice", referencedColumnName = "InvoiceID")
    public InvoiceEntity getInvoiceByInvoice() {
        return invoiceByInvoice;
    }

    public void setInvoiceByInvoice(InvoiceEntity invoiceByInvoice) {
        this.invoiceByInvoice = invoiceByInvoice;
    }

    @ManyToOne
    @JoinColumn(name = "RoomAssignment", referencedColumnName = "RoomAssignmentID")
    public RoomAssignmentEntity getRoomAssignmentByRoomAssignment() {
        return roomAssignmentByRoomAssignment;
    }

    public void setRoomAssignmentByRoomAssignment(RoomAssignmentEntity roomAssignmentByRoomAssignment) {
        this.roomAssignmentByRoomAssignment = roomAssignmentByRoomAssignment;
    }

    @ManyToOne
    @JoinColumn(name = "Article", referencedColumnName = "ArticleID")
    public ArticleEntity getArticleByArticle() {
        return articleByArticle;
    }

    public void setArticleByArticle(ArticleEntity articleByArticle) {
        this.articleByArticle = articleByArticle;
    }

    @ManyToOne
    @JoinColumn(name = "Arrangement", referencedColumnName = "ArrangementID")
    public ArrangementEntity getArrangementByArrangement() {
        return arrangementByArrangement;
    }

    public void setArrangementByArrangement(ArrangementEntity arrangementByArrangement) {
        this.arrangementByArrangement = arrangementByArrangement;
    }
}
