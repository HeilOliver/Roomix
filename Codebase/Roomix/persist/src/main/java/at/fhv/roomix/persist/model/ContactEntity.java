package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Contact", schema = "Roomix", catalog = "")
public class ContactEntity {
    private int contactId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String phoneNumber;
    private String street;
    private String houseNumber;
    private String place;
    private String postcode;
    private String country;
    private String email;
    private byte active;
    private Collection<ContactNoteEntity> contactNotesByContactId;
    private Collection<ContractingPartyEntity> contractingPartiesByContactId;
    private Collection<CreditCardEntity> creditCardsByContactId;
    private Collection<InvoiceEntity> invoicesByContactId;
    private Collection<PersonEntity> peopleByContactId;

    @Id
    @Column(name = "ContactID")
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
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
    @Column(name = "PhoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Active")
    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactEntity that = (ContactEntity) o;
        return contactId == that.contactId &&
                active == that.active &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(street, that.street) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(place, that.place) &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contactId, firstName, lastName, companyName, phoneNumber, street, houseNumber, place, postcode, country, email, active);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contactByContact")
    public Collection<ContactNoteEntity> getContactNotesByContactId() {
        return contactNotesByContactId;
    }

    public void setContactNotesByContactId(Collection<ContactNoteEntity> contactNotesByContactId) {
        this.contactNotesByContactId = contactNotesByContactId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contactByContact")
    public Collection<ContractingPartyEntity> getContractingPartiesByContactId() {
        return contractingPartiesByContactId;
    }

    public void setContractingPartiesByContactId(Collection<ContractingPartyEntity> contractingPartiesByContactId) {
        this.contractingPartiesByContactId = contractingPartiesByContactId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contactByContact")
    public Collection<CreditCardEntity> getCreditCardsByContactId() {
        return creditCardsByContactId;
    }

    public void setCreditCardsByContactId(Collection<CreditCardEntity> creditCardsByContactId) {
        this.creditCardsByContactId = creditCardsByContactId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contactByContact")
    public Collection<InvoiceEntity> getInvoicesByContactId() {
        return invoicesByContactId;
    }

    public void setInvoicesByContactId(Collection<InvoiceEntity> invoicesByContactId) {
        this.invoicesByContactId = invoicesByContactId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contactByContact")
    public Collection<PersonEntity> getPeopleByContactId() {
        return peopleByContactId;
    }

    public void setPeopleByContactId(Collection<PersonEntity> peopleByContactId) {
        this.peopleByContactId = peopleByContactId;
    }
}
