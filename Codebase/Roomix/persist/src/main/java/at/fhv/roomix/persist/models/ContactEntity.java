package at.fhv.roomix.persist.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private ContractingPartyEntity contractingParty;
    private Collection<ContactNoteEntity> contactNotes = new HashSet<>();
    private Collection<CreditCardEntity> creditCardsByContactId = new HashSet<>();
    private Collection<InvoiceEntity> invoicesByContactId = new HashSet<>();
    private Collection<PersonEntity> people = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contact",  orphanRemoval = true)
    public Collection<ContactNoteEntity> getContactNotes() {
        return contactNotes;
    }

    public void setContactNotes(Collection<ContactNoteEntity> contactNotesByContactId) {
        this.contactNotes = contactNotesByContactId;
    }

    @OneToOne(mappedBy = "contact")
    public ContractingPartyEntity getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(ContractingPartyEntity contractingPartyEntity) {
        this.contractingParty = contractingPartyEntity;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contactByContact")
    public Collection<CreditCardEntity> getCreditCardsByContactId() {
        return creditCardsByContactId;
    }

    public void setCreditCardsByContactId(Collection<CreditCardEntity> creditCardsByContactId) {
        this.creditCardsByContactId = creditCardsByContactId;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contactByContact")
    public Collection<InvoiceEntity> getInvoicesByContactId() {
        return invoicesByContactId;
    }

    public void setInvoicesByContactId(Collection<InvoiceEntity> invoicesByContactId) {
        this.invoicesByContactId = invoicesByContactId;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contact")
    public Collection<PersonEntity> getPeople() {
        return people;
    }

    public void setPeople(Collection<PersonEntity> peopleByContactId) {
        this.people = peopleByContactId;
    }
}
