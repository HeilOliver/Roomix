package at.fhv.roomix.domain.guest.contractingparty;

import at.fhv.roomix.domain.guest.contact.Contact;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * Company
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Company extends ContractingParty {
    public Company(Contact contact) {
        super(contact, ContractingPartyType.COMPANY);
    }
}
