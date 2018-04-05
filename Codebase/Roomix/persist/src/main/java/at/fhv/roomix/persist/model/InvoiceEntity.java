package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "invoice", schema = "roomix", catalog = "")
public class InvoiceEntity {
    private int invoiceId;
    private String name;
    private String street;
    private String place;
    private String postcode;
    private String country;
    private Timestamp dateOfIssue;
    private String status;
    private Collection<InvoicepositionEntity> invoicepositionsByInvoiceId;
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
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "Postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
                Objects.equals(name, that.name) &&
                Objects.equals(street, that.street) &&
                Objects.equals(place, that.place) &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(dateOfIssue, that.dateOfIssue) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(invoiceId, name, street, place, postcode, country, dateOfIssue, status);
    }

    @OneToMany(mappedBy = "invoiceByInvoice")
    public Collection<InvoicepositionEntity> getInvoicepositionsByInvoiceId() {
        return invoicepositionsByInvoiceId;
    }

    public void setInvoicepositionsByInvoiceId(Collection<InvoicepositionEntity> invoicepositionsByInvoiceId) {
        this.invoicepositionsByInvoiceId = invoicepositionsByInvoiceId;
    }

    @OneToMany(mappedBy = "invoiceByInvoice")
    public Collection<PaymentEntity> getPaymentsByInvoiceId() {
        return paymentsByInvoiceId;
    }

    public void setPaymentsByInvoiceId(Collection<PaymentEntity> paymentsByInvoiceId) {
        this.paymentsByInvoiceId = paymentsByInvoiceId;
    }
}
