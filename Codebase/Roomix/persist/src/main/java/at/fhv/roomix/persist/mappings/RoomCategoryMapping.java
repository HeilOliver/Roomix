package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.models.RoomCategoryEntity;
import at.fhv.roomix.persist.models.RoomEntity;

import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomCategoryMapping implements MapType<RoomCategoryEntity, RoomCategory> {

    static {
        Mapper mapper = Mapper.getInstance();
        mapper.addMapType(new RoomMapping(), RoomEntity.class, Room.class);
    }

    @Override
    public void map(RoomCategoryEntity source, RoomCategory destination, Mapper mapper) throws MappingException {
        destination.setDescription(source.getCategoryDescription());
        destination.setId(source.getRoomCategoryId());

        HashSet<Room> rooms = destination.getRooms();
        for (RoomEntity roomEntity : source.getRooms()) {
            Room room = new Room();
            room.setId(roomEntity.getRoomId());
            String status = roomEntity.getStatus();
            Room.RoomStatus roomStatus = Room.RoomStatus.valueOf(status);
            room.setRoomStatus(roomStatus);
            room.setCategory(destination);
            rooms.add(room);
        }
    }
}
