package at.fhv.roomix.domain.guest.model;

import java.util.Collection;

public class PersonDomain {

    private int personId;
    private byte isVip;
    private byte archive;
    private int contact;

    private String firstName;
    private String lastName;
    private GuestDomain contactByContact;
    private Collection<PersonReservationDomain> personReservationsByPersonId;
    private Proxy<Collection<PersonReservationDomain>, Integer> personReservationProxy;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public byte getIsVip() {
        return isVip;
    }

    public void setIsVip(byte isVip) {
        this.isVip = isVip;
    }

    public byte getArchive() {
        return archive;
    }

    public void setArchive(byte archive) {
        this.archive = archive;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
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

    public Collection<PersonReservationDomain> getPersonReservationsByPersonId() {
        if (personReservationProxy != null) {
            return (personReservationsByPersonId = personReservationProxy.get());
        } else {
            return personReservationsByPersonId;
        }
    }

    public GuestDomain getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(GuestDomain contactByContact) {
        this.contactByContact = contactByContact;
    }

    public void setPersonReservationsByPersonId(Collection<PersonReservationDomain> personReservationsByPersonId) {
        this.personReservationsByPersonId = personReservationsByPersonId;
    }

    public void setPersonReservationProxy(Proxy<Collection<PersonReservationDomain>, Integer> personReservationProxy) {
        this.personReservationProxy = personReservationProxy;
    }

}
