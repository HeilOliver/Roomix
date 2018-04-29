package at.fhv.roomix.domain.guest.model;

import java.sql.Timestamp;

public class InvoiceDomain {
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

    private GuestDomain contactByContact;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public Timestamp getDeterminationDate() {
        return determinationDate;
    }

    public void setDeterminationDate(Timestamp determinationDate) {
        this.determinationDate = determinationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GuestDomain getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(GuestDomain contactByContact) {
        this.contactByContact = contactByContact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}


