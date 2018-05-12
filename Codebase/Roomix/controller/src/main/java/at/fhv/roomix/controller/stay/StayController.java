package at.fhv.roomix.controller.stay;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.common.validator.Validator;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.controller.model.CheckInPojo;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.builder.accessbuilder.PersonBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.ReservationUnitBuilder;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistSaveException;
import at.fhv.roomix.persist.exception.PersistStateException;
import org.modelmapper.ModelMapper;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * StayController
 * 22.03.2018 OliverH
 */
class StayController implements IStayController{
    private final ISessionDomain sessionHandler = SessionFactory.getInstance();
    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public void setUnitsForCheckIn(long sessionId, CheckInPojo checkInPojo)
            throws ArgumentFaultException, SessionFaultException, ValidationFault, CheckInException, SaveFault {

        if (checkInPojo == null) throw new ArgumentFaultException();
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        Validator.validate(checkInPojo);

        try {
            Collection<PersonPojo> assignedPerson = checkInPojo.getAssignedPerson();

            // Check if Unit is still be CheckIn able
            ReservationUnit unit = ReservationUnitBuilder.get(checkInPojo.getUnit().getId());
            if(unit.canCheckedIn()) {
                throw new CheckInException("Unit is already CheckedIn");
            }

            // Assign Person to Units
            for (PersonPojo pojo : assignedPerson) {
                Person person = PersonBuilder.get(pojo.getId());
                assert person != null;
                mapper.map(pojo, person);
                PersonBuilder.update(person);
                unit.addGuest(person);
            }

            // Room Is Assigned?
            if(!unit.hasRoom()) {
                // Maybe here add Daniels Method to find a room??
                throw new CheckInException("Unit has no Room");
            }

            unit.checkIn();
            ReservationUnitBuilder.update(unit);
            EntityFactory.commitAll();
        } catch (PersistStateException | BuilderLoadException | BuilderUpdateException | PersistSaveException e) {
            throw new SaveFault("An Exception occurred", e);
        }
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservations(long sessionId, String query) throws GetFault, SessionFaultException {
        return ReservationControllerFactory.getInstance().getSearchedReservation(sessionId, query);
    }
}
