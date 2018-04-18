package at.fhv.roomix.controller.contact;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;

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

    Collection<ContactPojo> getAllContacts(long sessionId) throws SessionFaultException;

    Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException;

    void updateContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException;
}
