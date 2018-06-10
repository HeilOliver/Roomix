package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.RoomCategoryPojo;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.domain.room.RoomCategory;

/**
 * Roomix
 * at.fhv.roomix.implement.mapping
 * CategoryMapping
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryMapping implements MapType<RoomCategory, RoomCategoryPojo> {
    @Override
    public void map(RoomCategory source, RoomCategoryPojo destination, Mapper mapper) throws MappingException {
        destination.setId(source.getId());
        destination.setDescription(source.getDescription());
    }
}
