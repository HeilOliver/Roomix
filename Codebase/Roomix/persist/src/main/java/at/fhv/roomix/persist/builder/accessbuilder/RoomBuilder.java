package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.persist.dataaccess.factory.RoomFactory;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.mappings.RoomMapping;
import at.fhv.roomix.persist.models.RoomEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.accessbuilder
 * RoomBuilder
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomBuilder {
    private static Mapper mapper = Mapper.getInstance();

    static {
        //mapper.addMapType(new RoomMapping(), RoomEntity.class, Room.class);
    }

    static void updateRoom(Room room) throws BuilderUpdateException {
        try {
            RoomEntity roomEntity = RoomFactory.getInstance().get(room.getId());
            roomEntity.setStatus(room.getRoomStatus().toString());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException();
        }
    }

}
