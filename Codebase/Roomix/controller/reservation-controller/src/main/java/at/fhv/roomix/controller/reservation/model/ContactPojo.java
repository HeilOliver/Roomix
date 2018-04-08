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

    @NotNull(message = "firstName cannot be null")
    @Size(min = 1, max = 50, message = "firstName must be between 1 and 200 characters")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @Size(min = 1, max = 50, message = "lastName must be between 1 and 200 characters")
    private String lastName;

    @Size(max = 50, message = "companyName must be between 1 and 200 characters")
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

    public ContactPojo() {
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
                Objects.equals(place, that.place) &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, firstName, lastName, companyName, phoneNumber, street, place, postcode, country, email);
    }
}
