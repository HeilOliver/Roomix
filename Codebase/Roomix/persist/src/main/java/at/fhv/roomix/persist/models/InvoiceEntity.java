package at.fhv.roomix.persist.models;

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
    private String firstName;
    private String lastName;
    private String companyName;
    private String street;
    private String houseNumber;
    private String place;
    private String postcode;
    private String country;
    private int contact;
    private Timestamp determinationDate;
    private String status;
    private ContactEntity contactByContact;
    private Collection<InvoicePositionEntity> invoicePositionsByInvoiceId;
    private Collection<PaymentEntity> paymentsByInvoiceId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    @Column(name = "HouseNumber")
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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
    @OneToMany(mappedBy = "invoice")
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
