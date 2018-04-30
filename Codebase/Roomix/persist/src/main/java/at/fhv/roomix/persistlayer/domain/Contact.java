package at.fhv.roomix.persistlayer.domain;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.domain
 * Contact
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Contact {
    private int id;
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

    private boolean isActive;
    private boolean isContractingParty;

    private Collection<CreditCard> creditCards = new HashSet<>();
    private Collection<ContactNote> notes = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isContractingParty() {
        return isContractingParty;
    }

    public void setContractingParty(boolean contractingParty) {
        isContractingParty = contractingParty;
    }

    public Collection<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Collection<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public Collection<ContactNote> getNotes() {
        return notes;
    }

    public void setNotes(Collection<ContactNote> notes) {
        this.notes = notes;
    }
}
