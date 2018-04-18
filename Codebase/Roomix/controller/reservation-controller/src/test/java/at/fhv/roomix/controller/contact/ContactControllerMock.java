package at.fhv.roomix.controller.contact;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationMock
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactControllerMock implements IContactController {

    @Override
    public Collection<ContactPojo> getAllContacts(long sessionId) throws SessionFaultException {
        return null;
    }

    @Override
    public Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException {
        return null;
    }

    @Override
    public void updateContact(long sessionId, ContactPojo contactPojo)
            throws SessionFaultException, ValidationFault, ArgumentFaultException {

    }
}
