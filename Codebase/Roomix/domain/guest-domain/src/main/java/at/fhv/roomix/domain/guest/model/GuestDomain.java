package at.fhv.roomix.domain.guest.model;


import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.domain.guest.model
 * GuestDomain
 * 04/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class GuestDomain {

    private int contactId;
    private String fname;
    private String lname;
    private String companyName;
    private String phoneNumber;
    private String street;
    private Integer houseNumber;
    private String place;
    private String postcode;
    private String country;
    private String email;
    private byte active;

    private Collection<ContactNoteDomain> contactNotes;
    private Collection<CreditCardDomain> creditCards;
    private Collection<ContractingPartyDomain> contractingPartys;
    private Collection<PersonDomain> personDomains;


    public Collection<PersonDomain> getPersonDomains() {
        return personDomains;
    }

    public void setPersonDomains(Collection<PersonDomain> personDomains) {
        this.personDomains = personDomains;
    }


    public Collection<ContractingPartyDomain> getContractingPartys() {
        return contractingPartys;
    }

    public void setContractingPartys(Collection<ContractingPartyDomain> contractingPartys) {
        this.contractingPartys = contractingPartys;
    }


    public Collection<CreditCardDomain> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Collection<CreditCardDomain> creditCards) {
        this.creditCards = creditCards;
    }

    public Collection<ContactNoteDomain> getContactNotes() {
        return contactNotes;
    }

    public void setContactNotes(Collection<ContactNoteDomain> contactNotes) {
        this.contactNotes = contactNotes;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
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

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

}
