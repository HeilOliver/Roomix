package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "Contact", schema = "Roomix")
public class ContactEntity implements Serializable{
    private int contactId;
    private String forename;
    private String surname;
    private String companyName;
    private String phoneNumber;
    private String street;
    private String place;
    private String postcode;
    private String country;
    private String email;
    private byte active;
    private Collection<CreditCardEntity> creditCardsByContactId;
    private Collection<PersonEntity> peopleByContactId;

    @Id
    @Column(name = "ContactID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "Forename")
    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    @Basic
    @Column(name = "Surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                Objects.equals(forename, that.forename) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(street, that.street) &&
                Objects.equals(place, that.place) &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, forename, surname, companyName, phoneNumber, street, place, postcode, country, email, active);
    }

    @OneToMany(mappedBy = "contactByContact")
    public Collection<CreditCardEntity> getCreditCardsByContactId() {
        return creditCardsByContactId;
    }

    public void setCreditCardsByContactId(Collection<CreditCardEntity> creditCardsByContactId) {
        this.creditCardsByContactId = creditCardsByContactId;
    }

    @OneToMany(mappedBy = "contactByContact")
    public Collection<PersonEntity> getPeopleByContactId() {
        if (peopleByContactId == null) peopleByContactId = new HashSet<>();
        return peopleByContactId;
    }

    public void setPeopleByContactId(Collection<PersonEntity> peopleByContactId) {
        this.peopleByContactId = peopleByContactId;
    }
}
