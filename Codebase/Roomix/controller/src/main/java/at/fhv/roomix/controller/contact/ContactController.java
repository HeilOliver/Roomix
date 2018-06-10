package at.fhv.roomix.controller.contact;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.common.validator.Validator;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.builder.accessbuilder.ContactBuilder;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistSaveException;
import at.fhv.roomix.persist.exception.PersistStateException;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.implement.contact
 * ReservationController
 * 22.03.2018 sge
 * <p>
 * The Implementation for the ContactController itself
 */
class ContactController implements IContactController {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    private final ISessionDomain sessionHandler = SessionFactory.getInstance();

    @Override
    public void updateContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException, SaveFault {
        if (contactPojo == null) throw new ArgumentFaultException();
        Validator.validate(contactPojo);
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            Contact contact = ContactBuilder.getContact(contactPojo.getContactId());
            mapper.map(contactPojo, contact);
            ContactBuilder.update(contact);
            EntityFactory.commitAll();
        } catch (BuilderLoadException | BuilderUpdateException | PersistStateException | PersistSaveException e) {
            throw new SaveFault("Exception occurred in save procedure, see inner exception for detail", e);
        } finally {
            EntityFactory.stashChanges();
        }
    }

    public Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException, GetFault {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        try {
            String[] split = query.toLowerCase().split(" ");
            Collection<Contact> contacts = ContactBuilder.getContacts();

            Set<ContactPojo> resultSet = new HashSet<>();
            for (String splicedQuery : split) {
                resultSet.addAll(contacts.stream()
                        .filter(c -> c.getFirstName().toLowerCase().contains(splicedQuery) ||
                                c.getLastName().toLowerCase().contains(splicedQuery) ||
                                c.getStreet().toLowerCase().contains(splicedQuery) ||
                                c.getPostcode().toLowerCase().contains(splicedQuery) ||
                                c.getPlace().toLowerCase().contains(splicedQuery))
                        .map((contact) -> mapper.map(contact, ContactPojo.class))
                        .collect(Collectors.toSet()));
            }
            return resultSet;
        } catch (BuilderLoadException e) {
            throw new GetFault("Exception by loading data, see inner exception fore more details", e);
        }
    }
}
