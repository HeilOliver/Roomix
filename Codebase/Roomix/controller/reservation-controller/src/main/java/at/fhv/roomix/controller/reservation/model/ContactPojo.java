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

    @NotNull(message = "forename cannot be null")
    @Size(min = 1, max = 50, message = "forename must be between 1 and 200 characters")
    private String forename;

    @NotNull(message = "surname cannot be null")
    @Size(min = 1, max = 50, message = "surname must be between 1 and 200 characters")
    private String surname;

    private String companyName;

    @NotNull(message = "phoneNumber cannot be null")
    private String phoneNumber;

    @NotNull(message = "street cannot be null")
    private String street;

    @NotNull(message = "place cannot be null")
    private String place;

    @NotNull(message = "postcode cannot be null")
    private String postcode;

    @NotNull(message = "country cannot be null")
    private String country;

    @Email(message = "Email should be valid")
    private String email;

    private byte active;

    public ContactPojo() {
    }

    public ContactPojo(int contactId) {
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        ContactPojo that = (ContactPojo) o;
        return contactId == that.contactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }
}
