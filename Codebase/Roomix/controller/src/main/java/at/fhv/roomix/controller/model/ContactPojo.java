package at.fhv.roomix.controller.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Roomix
 * at.fhv.roomix.controller.contact.contact
 * ContactPojo
 * 18.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ContactPojo {
    private int contactId;

    @NotNull(message = "firstName cannot be null")
    @Size(min = 1, max = 50, message = "firstName must be between 1 and 200 characters")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @Size(min = 1, max = 50, message = "lastName must be between 1 and 200 characters")
    private String lastName;

    @Size(max = 50, message = "companyName must be between 0 and 200 characters")
    private String companyName;

    @NotNull(message = "phoneNumber cannot be null")
    @Size(min = 1)
    private String phoneNumber;

    @NotNull(message = "street cannot be null")
    @Size(min = 1)
    private String street;

    @NotNull(message = "houseNumber cannot be null")
    @Size(min = 1)
    private String houseNumber;

    @NotNull(message = "place cannot be null")
    @Size(min = 1)
    private String place;

    @NotNull(message = "postcode cannot be null")
    @Size(min = 1)
    private String postcode;

    @NotNull(message = "country cannot be null")
    @Size(min = 1)
    private String country;

    @Email(message = "Email should be valid")
    @Size(min = 1)
    private String email;

    // 0 = none, 100 = private, 200 = company, 300 = travelAgent
    private int contractingPartyType;

    public ContactPojo() {
    }

    public int getContractingPartyType() {
        return contractingPartyType;
    }

    public void setContractingPartyType(int contractingPartyType) {
        this.contractingPartyType = contractingPartyType;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
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

    public String getPhoneNumber() {
        return phoneNumber;
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
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(street, that.street) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(place, that.place) &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(email, that.email) &&
                Objects.equals(houseNumber, that.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, firstName, lastName, companyName, phoneNumber, street, houseNumber, place, postcode, country, email);
    }
}