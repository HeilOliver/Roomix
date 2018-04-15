package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Invoice", schema = "Roomix", catalog = "")
public class InvoiceEntity {
    private int invoiceId;
    private int contact;
    private Timestamp determinationDate;
    private String status;
    private ContactEntity contactByContact;
    private Collection<InvoicePositionEntity> invoicePositionsByInvoiceId;
    private Collection<PaymentEntity> paymentsByInvoiceId;

    @Id
    @Column(name = "InvoiceID")
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Basic
    @Column(name = "Contact", insertable = false, updatable = false)
    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
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
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceEntity that = (InvoiceEntity) o;
        return invoiceId == that.invoiceId &&
                contact == that.contact &&
                Objects.equals(determinationDate, that.determinationDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(invoiceId, contact, determinationDate, status);
    }

    @ManyToOne
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID", nullable = false)
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "invoiceByInvoice")
    public Collection<InvoicePositionEntity> getInvoicePositionsByInvoiceId() {
        return invoicePositionsByInvoiceId;
    }

    public void setInvoicePositionsByInvoiceId(Collection<InvoicePositionEntity> invoicePositionsByInvoiceId) {
        this.invoicePositionsByInvoiceId = invoicePositionsByInvoiceId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "invoiceByInvoice")
    public Collection<PaymentEntity> getPaymentsByInvoiceId() {
        return paymentsByInvoiceId;
    }

    public void setPaymentsByInvoiceId(Collection<PaymentEntity> paymentsByInvoiceId) {
        this.paymentsByInvoiceId = paymentsByInvoiceId;
    }
}
