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
import org.modelmapper.ModelMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

        IAbstractDomainBuilder guestBuilder = GuestDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();

        GuestDomain guestDomain = modelMapper.map(contactPojo, GuestDomain.class);

        guestBuilder.set(guestDomain);
    }

    @Override
    public Collection<ContactPojo> getAllContacts(long sessionId) throws SessionFaultException {

        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder guestBuilder = GuestDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        HashSet<GuestDomain> guestDomainSet = new HashSet<>(guestBuilder.getAll());
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
}
