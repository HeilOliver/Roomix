package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.models.RoomEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * RoomMapping
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomMapping implements MapType<RoomEntity, Room> {
    @Override
    public void map(RoomEntity source, Room destination, Mapper mapper) throws MappingException {
        destination.setId(source.getRoomId());
        try {
            RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(
                    source.getRoomCategory().getRoomCategoryId());
            destination.setCategory(roomCategory);
        } catch (BuilderLoadException e) {
            // TODO LogHere
        }
        String status = source.getStatus();
        Room.RoomStatus roomStatus = Room.RoomStatus.valueOf(status);
        destination.setRoomStatus(roomStatus);
    }
}
