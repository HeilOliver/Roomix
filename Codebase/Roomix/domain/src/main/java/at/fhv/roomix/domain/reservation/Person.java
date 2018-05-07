package at.fhv.roomix.domain.reservation;

import at.fhv.roomix.domain.guest.contact.Contact;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * Person
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private boolean isVip;
    private boolean archive;
    private Contact contact;

    public Person() {
    }

    public Person(Contact contact) {
        this.contact = contact;
        firstName = contact.getFirstName();
        lastName = contact.getLastName();
        archive = true;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        archive = false;
    }

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

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
