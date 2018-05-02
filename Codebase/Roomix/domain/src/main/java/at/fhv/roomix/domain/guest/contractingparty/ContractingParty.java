package at.fhv.roomix.domain.guest.contractingparty;

import at.fhv.roomix.domain.guest.contact.Contact;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * ContractingParty
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class ContractingParty {
    private Contact contact;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContractingParty(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
