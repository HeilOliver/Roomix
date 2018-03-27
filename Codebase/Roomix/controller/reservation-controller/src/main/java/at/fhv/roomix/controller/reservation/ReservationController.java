package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.exeption.FaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;

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
 *
 * The Implementation for the ReservationController itself
 */
class ReservationController implements IReservationController {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final ISessionDomain sessionHandler = SessionFactory.getInstance();

    private <T> void validate(T object) throws FaultException {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (violations.isEmpty()) return;

        Set<String> strings = new HashSet<>();
        violations.forEach((v) -> strings.add(v.getMessage()));
        throw new ValidationFault(strings);
    }

    @Override
    public void newContact(long sessionId, ContactPojo contactPojo) throws FaultException {
        validate(contactPojo);
        if (!sessionHandler.isValidFor(sessionId, null))  throw new SessionFaultException();

    }

    @Override
    public Collection<ContactPojo> getAllContacts(long sessionId) throws FaultException {
        if (!sessionHandler.isValidFor(sessionId, null))  throw new SessionFaultException();
        return null;
    }

    @Override
    public void updateContact(long sessionId, ContactPojo contactPojo) throws FaultException {
        validate(contactPojo);
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

    }
}
