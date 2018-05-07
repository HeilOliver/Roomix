package at.fhv.roomix.controller.contact;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.contact.model.ContactPojo;

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

    Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException, GetFault;

    void updateContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException, SaveFault;
}
