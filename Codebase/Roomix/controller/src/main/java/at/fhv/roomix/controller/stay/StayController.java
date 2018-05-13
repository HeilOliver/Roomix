package at.fhv.roomix.controller.stay;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.common.validator.Validator;
import at.fhv.roomix.controller.model.CheckInReply;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.controller.model.CheckInPojo;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.builder.accessbuilder.PersonBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.ReservationUnitBuilder;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.exception.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * StayController
 * 22.03.2018 OliverH
 */
class StayController implements IStayController{
    private final ISessionDomain sessionHandler = SessionFactory.getInstance();
    private static final Mapper mapper = Mapper.getInstance();

    static {

    }

    @Override
    public CheckInReply setUnitsForCheckIn(long sessionId, CheckInPojo checkInPojo)
            throws ArgumentFaultException, SessionFaultException, ValidationFault, CheckInException, SaveFault {

        if (checkInPojo == null) throw new ArgumentFaultException();
        if (!sessionHandler.isValidFor(sessionId, null)) throw new SessionFaultException();
        Validator.validate(checkInPojo);

        try {
            Collection<PersonPojo> assignedPerson = checkInPojo.getAssignedPerson();

            // Check if Unit is still be CheckIn able
            ReservationUnit unit = ReservationUnitBuilder.get(checkInPojo.getUnit().getId());
            if(!unit.canCheckedIn()) {
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

            LocalDate localDate = unit.getAssignedRooms().keySet().stream().min(Comparator.comparing(LocalDate::toEpochDay)).get();
            Room firstRoom = unit.getAssignedRooms().get(localDate);
            Optional<Map.Entry<LocalDate, Room>> roomChange = unit.getAssignedRooms().entrySet().stream()
                    .sorted(Comparator.comparing(localDateRoomEntry -> localDateRoomEntry.getKey().toEpochDay()))
                    .filter(entry -> !entry.getValue().equals(firstRoom))
                    .findFirst();
            if (roomChange.isPresent()) {
                localDate = roomChange.get().getKey();
            }
            CheckInReply.Reply reply = CheckInReply.Reply.OK;
            if (firstRoom.isDirty()) {
                reply = CheckInReply.Reply.DIRTY;
            }
            if (firstRoom.isMultipleOccupied()) {
                reply = CheckInReply.Reply.DOUBLE_OCCUPATION;
            }
            CheckInReply checkInReply = new CheckInReply(Integer.toString(firstRoom.getId()), localDate, reply);

            unit.checkIn();
            ReservationUnitBuilder.update(unit);
            EntityFactory.commitAll();

            return checkInReply;
        } catch (PersistStateException | BuilderLoadException | BuilderUpdateException | PersistSaveException e) {
            throw new SaveFault("An Exception occurred", e);
        }
    }

    @Override
    public Collection<ReservationPojo> getSearchedReservations(long sessionId, String query) throws GetFault, SessionFaultException {
        return ReservationControllerFactory.getInstance().getSearchedReservation(sessionId, query);
    }
}
