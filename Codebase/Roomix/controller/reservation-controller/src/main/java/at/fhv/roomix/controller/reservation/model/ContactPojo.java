package at.fhv.roomix.controller.reservation.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ContactPojo
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactPojo {
    private int contactId;

    @NotNull(message = "fname cannot be null")
    @Size(min = 1, max = 50, message = "fname must be between 1 and 200 characters")
    private String fname;

    @NotNull(message = "lname cannot be null")
    @Size(min = 1, max = 50, message = "lname must be between 1 and 200 characters")
    private String lname;

    @Size(max = 50, message = "companyName must be between 1 and 200 characters")
    private String companyName;

    @NotNull(message = "phoneNumber cannot be null")
    private String phoneNumber;

    @NotNull(message = "housenumber cannot be null")
    private int houseNumber;

    @NotNull(message = "street cannot be null")
    private String street;

    @NotNull(message = "houseNumber cannot be null")
    private String houseNumber;

    @NotNull(message = "place cannot be null")
    private String place;

    @NotNull(message = "postcode cannot be null")
    private String postcode;

    @NotNull(message = "country cannot be null")
    private String country;

    @Email(message = "Email should be valid")
    private String email;

    public ContactPojo() {
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
        ContactPojo that = (ContactPojo) o;
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
                Objects.equals(email, that.email)&&
                Objects.equals(houseNumber, that.houseNumber);
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(contactId, fname, lname, companyName, phoneNumber, street, houseNumber, place, postcode, country, email);
=======
        return Objects.hash(contactId, firstName, lastName, companyName,houseNumber, phoneNumber, street, place, postcode, country, email);
>>>>>>> c32c09adf744a334e41b222cb00296d647cffb47
    }
}
