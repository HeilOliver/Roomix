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
    private ContractingPartyType type;

    public ContractingParty(Contact contact, ContractingPartyType type) {
        this.contact = contact;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ContractingPartyType getType() {
        return type;
    }

    public enum ContractingPartyType {
        INDIVIDUAL,
        TRAVEL_AGENT,
        COMPANY
    }
}
