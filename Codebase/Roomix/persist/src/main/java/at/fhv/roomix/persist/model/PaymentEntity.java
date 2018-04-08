package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "payment", schema = "roomix", catalog = "")
public class PaymentEntity {
    private int paymentId;
    private int invoice;
    private int paymentType;
    private int amount;
    private Timestamp dateOfIssue;
    private Date dueDate;
    private Date paidDate;
    private InvoiceEntity invoiceByInvoice;
    private PaymenttypeEntity paymenttypeByPaymentType;

    @Id
    @Column(name = "PaymentID")
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "Invoice", insertable=false, updatable=false)
    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "PaymentType", insertable=false, updatable=false)
    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
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
                paymentType == that.paymentType &&
                amount == that.amount &&
                Objects.equals(dateOfIssue, that.dateOfIssue) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(paidDate, that.paidDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(paymentId, invoice, paymentType, amount, dateOfIssue, dueDate, paidDate);
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
    public PaymenttypeEntity getPaymenttypeByPaymentType() {
        return paymenttypeByPaymentType;
    }

    public void setPaymenttypeByPaymentType(PaymenttypeEntity paymenttypeByPaymentType) {
        this.paymenttypeByPaymentType = paymenttypeByPaymentType;
    }
}
