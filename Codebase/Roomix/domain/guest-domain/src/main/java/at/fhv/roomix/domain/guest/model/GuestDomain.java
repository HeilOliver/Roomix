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

    private Collection<ContactNoteDomain> contactNotesByContactId;
    private Collection<ContractingPartyDomain> contractingPartiesByContactId;
    private Collection<CreditCardDomain> creditCardsByContactId;
    private Collection<InvoiceDomain> invoicesByContactId;
    private Collection<PersonDomain> peopleByContactId;

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

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public Collection<ContactNoteDomain> getContactNotesByContactId() {
        return contactNotesByContactId;
    }

    public void setContactNotesByContactId(Collection<ContactNoteDomain> contactNotesByContactId) {
        this.contactNotesByContactId = contactNotesByContactId;
    }

    public Collection<ContractingPartyDomain> getContractingPartiesByContactId() {
        return contractingPartiesByContactId;
    }

    public void setContractingPartiesByContactId(Collection<ContractingPartyDomain> contractingPartiesByContactId) {
        this.contractingPartiesByContactId = contractingPartiesByContactId;
    }

    public Collection<CreditCardDomain> getCreditCardsByContactId() {
        return creditCardsByContactId;
    }

    public void setCreditCardsByContactId(Collection<CreditCardDomain> creditCardsByContactId) {
        this.creditCardsByContactId = creditCardsByContactId;
    }

    public Collection<InvoiceDomain> getInvoicesByContactId() {
        return invoicesByContactId;
    }

    public void setInvoicesByContactId(Collection<InvoiceDomain> invoicesByContactId) {
        this.invoicesByContactId = invoicesByContactId;
    }

    public Collection<PersonDomain> getPeopleByContactId() {
        return peopleByContactId;
    }

    public void setPeopleByContactId(Collection<PersonDomain> peopleByContactId) {
        this.peopleByContactId = peopleByContactId;
    }
}
