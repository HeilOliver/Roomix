package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.GuestDomainBuilder;
import at.fhv.roomix.persist.factory.IAbstractDomainBuilder;
import at.fhv.roomix.persist.model.ContactEntity;
import org.modelmapper.ModelMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * ReservationController
 * 22.03.2018 sge
 * <p>
 * The Implementation for the ReservationController itself
 */
class ReservationController implements IReservationController {
    private final ISessionDomain sessionHandler = SessionFactory.getInstance();
    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private <T> void validate(T object) throws ValidationFault {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (violations.isEmpty()) return;

        Set<String> strings = new HashSet<>();
        violations.forEach((v) -> strings.add(v.getMessage()));
        throw new ValidationFault(strings);
    }

    @Override
    public void newContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

        if (contactPojo == null) throw new ArgumentFaultException();
        validate(contactPojo);
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<GuestDomain, ContactEntity> guestBuilder = GuestDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();

        GuestDomain guestDomain = modelMapper.map(contactPojo, GuestDomain.class);

        guestBuilder.set(guestDomain);
    }

    @Override
    public Collection<ContactPojo> getAllContacts(long sessionId) throws SessionFaultException {

        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<GuestDomain, ContactEntity> guestBuilder = GuestDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        HashSet<GuestDomain> guestDomainSet = new HashSet<GuestDomain>(guestBuilder.getAll());
        HashSet<ContactPojo> contactPojoSet = new HashSet<>();

        guestDomainSet.forEach(contact -> contactPojoSet.add(modelMapper.map(contact, ContactPojo.class)));

        return contactPojoSet;
    }

    @Override
    public void updateContact(long sessionId, ContactPojo contactPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {

        if (contactPojo == null) throw new ArgumentFaultException();
        validate(contactPojo);
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        newContact(sessionId, contactPojo);
    }

    public Collection<ContactPojo> getSearchedContacts(long sessionId, String query) throws SessionFaultException {

        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        Collection<ContactPojo> contactPojoSet = getAllContacts(sessionId);

        HashSet<ContactPojo> filteredPojoSet = new HashSet<>();

        List<ContactPojo> filteredPojo = contactPojoSet.stream()
                .filter(c -> c.getFname().contains(query) ||
                        c.getLname().contains(query) ||
                        c.getStreet().contains(query) ||
                        c.getPostcode().contains(query) ||
                        c.getPlace().contains(query))
                .collect(Collectors.toList());
        filteredPojoSet.clear();
        filteredPojoSet.addAll(filteredPojo);

        return filteredPojoSet;
    }
}
