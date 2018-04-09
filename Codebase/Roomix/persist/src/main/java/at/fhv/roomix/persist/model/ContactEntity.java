package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Contact", schema = "roomix", catalog = "")
public class ContactEntity {
    private int contactId;
    private String fname;
    private String lname;
    private String companyName;
    private String phoneNumber;
    private String street;
    private String houseNumber;
    private String place;
    private String postcode;
    private String country;
    private String email;
    private Collection<ContactnoteEntity> contactnotesByContactId;
    private Collection<ContractingpartyEntity> contractingpartiesByContactId;
    private Collection<CreditcardEntity> creditcardsByContactId;
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
    @Column(name = "Fname")
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Basic
    @Column(name = "Lname")
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactEntity that = (ContactEntity) o;
        return contactId == that.contactId &&
                Objects.equals(fname, that.fname) &&
                Objects.equals(lname, that.lname) &&
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
        return Objects.hash(contactId, fname, lname, companyName, phoneNumber, street, houseNumber, place, postcode, country, email);
    }

    @OneToMany(mappedBy = "contactByContact")
    public Collection<ContactnoteEntity> getContactnotesByContactId() {
        return contactnotesByContactId;
    }

    public void setContactnotesByContactId(Collection<ContactnoteEntity> contactnotesByContactId) {
        this.contactnotesByContactId = contactnotesByContactId;
    }

    @OneToMany(mappedBy = "contactByContact")
    public Collection<ContractingpartyEntity> getContractingpartiesByContactId() {
        return contractingpartiesByContactId;
    }

    public void setContractingpartiesByContactId(Collection<ContractingpartyEntity> contractingpartiesByContactId) {
        this.contractingpartiesByContactId = contractingpartiesByContactId;
    }

    @OneToMany(mappedBy = "contactByContact")
    public Collection<CreditcardEntity> getCreditcardsByContactId() {
        return creditcardsByContactId;
    }

    public void setCreditcardsByContactId(Collection<CreditcardEntity> creditcardsByContactId) {
        this.creditcardsByContactId = creditcardsByContactId;
    }

    @OneToMany(mappedBy = "contactByContact")
    public Collection<PersonEntity> getPeopleByContactId() {
        return peopleByContactId;
    }

    public void setPeopleByContactId(Collection<PersonEntity> peopleByContactId) {
        this.peopleByContactId = peopleByContactId;
    }
}
