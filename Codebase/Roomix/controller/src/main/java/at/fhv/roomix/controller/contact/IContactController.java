package at.fhv.roomix.controller.contact;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.model.ContactPojo;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.contact
 * IContactController
 * 18/04/2018 sge
 * <p>
 * Enter Description here
 */
public interface IContactController {

    /**
     * Returns all Contacts for the given query string
     */
    Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException, GetFault;

    /**
     * Updates/Creates the given Contact
     */
    void updateContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException, SaveFault;
}
