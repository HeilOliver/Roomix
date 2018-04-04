package at.fhv.roomix.domain.guest.model;

import at.fhv.roomix.persist.model.ContactEntity;

/**
 * Roomix
 * at.fhv.roomix.domain.guest.model
 * GuestDomain
 * 04/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class GuestDomain {

    private ContactEntity contactEntity;

    public GuestDomain(ContactEntity contactEntity){
        this.contactEntity = contactEntity;
    }


}
