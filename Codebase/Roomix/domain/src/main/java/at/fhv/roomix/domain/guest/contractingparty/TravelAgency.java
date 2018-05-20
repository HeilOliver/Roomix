package at.fhv.roomix.domain.guest.contractingparty;

import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.room.RoomCategory;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.domain.guest.contractingparty
 * TravelAgency
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class TravelAgency extends ContractingParty {
    public TravelAgency(Contact contact) {
        super(contact, ContractingPartyType.TRAVEL_AGENT);
    }

    public int getQuota(LocalDate statusDate, RoomCategory roomCategory) {
        return 0;
    }
}
