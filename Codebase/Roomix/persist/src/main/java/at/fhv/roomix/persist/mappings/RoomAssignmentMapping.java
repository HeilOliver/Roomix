package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.reservation.RoomAssignment;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.persist.models.ReservationUnitEntity;
import at.fhv.roomix.persist.models.RoomAssignmentEntity;
import at.fhv.roomix.persist.models.RoomEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomAssignmentMapping implements MapType<RoomAssignmentEntity, RoomAssignment> {



    @Override
    public void map(RoomAssignmentEntity source, RoomAssignment destination, Mapper mapper) throws MappingException {
        destination.setId(source.getRoomAssignmentId());
        destination.setDepartureDate(source.getDepartureDate().toLocalDate());
        destination.setArrivalDate(source.getArrivalDate().toLocalDate());

        ReservationUnitEntity unit = source.getReservationUnit();
        destination.setUnit(mapper.map(unit, ReservationUnit.class));

        RoomEntity room = source.getRoom();
        destination.setAssignedRoom(mapper.map(room, Room.class));
    }

    @Override
    public void mapReverse(RoomAssignment source, RoomAssignmentEntity destination, Mapper mapper) throws MappingException {

    }
}
