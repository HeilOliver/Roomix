package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Payment", schema = "Roomix", catalog = "")
public class PaymentEntity {
    private int paymentId;
    private int invoice;
    private int totalPrice;
    private Timestamp determinationDate;
    private Date dueDate;
    private Date paidDate;
    private InvoiceEntity invoiceByInvoice;
    private PaymentTypeEntity paymentType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "Invoice", insertable = false, updatable = false)
    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "TotalPrice")
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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
    @Column(name = "DueDate")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "PaidDate")
    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return paymentId == that.paymentId &&
                invoice == that.invoice &&
                totalPrice == that.totalPrice &&
                Objects.equals(determinationDate, that.determinationDate) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(paidDate, that.paidDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, invoice, totalPrice, determinationDate, dueDate, paidDate);
    }

    @ManyToOne
    @JoinColumn(name = "Invoice", referencedColumnName = "InvoiceID", nullable = false)
    public InvoiceEntity getInvoiceByInvoice() {
        return invoiceByInvoice;
    }

    public void setInvoiceByInvoice(InvoiceEntity invoiceByInvoice) {
        this.invoiceByInvoice = invoiceByInvoice;
    }

    @ManyToOne
    @JoinColumn(name = "PaymentType", referencedColumnName = "PaymentTypeID", nullable = false)
    public PaymentTypeEntity getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeEntity paymentTypeByPaymentType) {
        this.paymentType = paymentTypeByPaymentType;
    }
}
