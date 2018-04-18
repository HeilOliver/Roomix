package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.exeption.SessionFaultException;
import at.fhv.roomix.controller.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ReservationPojo;
import at.fhv.roomix.domain.guest.model.ReservationDomain;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.IAbstractDomainBuilder;
import at.fhv.roomix.persist.factory.ReservationDomainBuilder;
import at.fhv.roomix.persist.model.ReservationEntity;
import org.modelmapper.ModelMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * ReservationController
 * 18.04.2018 Robert S.
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
    public Collection<ReservationPojo> getAllReservation(long sessionId) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        IAbstractDomainBuilder<ReservationDomain, ReservationEntity> reservationBuilder = ReservationDomainBuilder.getInstance();
        ModelMapper modelMapper = new ModelMapper();
        HashSet<ReservationDomain> reservationDomainSet = new HashSet<>(reservationBuilder.getAll());
        HashSet<ReservationPojo> reservationPojoSet = new HashSet<>();

        reservationDomainSet.forEach(reservation -> reservationPojoSet.add(modelMapper.map(reservation, ReservationPojo.class)));

        return reservationPojoSet;
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservation(long sessionId, String query) throws SessionFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();

        Collection<ReservationPojo> reservationPojoSet = getAllReservation(sessionId);

        String[] split = query.toLowerCase().split(" ");

        Set<ReservationPojo> resultSet = new HashSet<>(reservationPojoSet);
        for (String splitedQuery : split) {
            resultSet = resultSet.stream()
                    .filter(c -> c.getContractingParty().toString().toLowerCase().contains(splitedQuery) ||
                            c.getPersons().toString().toLowerCase().contains(splitedQuery) ||
                            c.getComment().toLowerCase().contains(splitedQuery) ||
                            c.getUnits().toString().toLowerCase().contains(splitedQuery))
                    .collect(Collectors.toSet());
        }

        return resultSet;
    }

    @Override
    public void updateReservation(long sessionId, ReservationPojo reservationPojo) throws SessionFaultException, ValidationFault, ArgumentFaultException {
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();


    }

}
