package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.models.RoomCategoryEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * CategoryMapping
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryMapping implements MapType<RoomCategoryEntity, RoomCategory> {
    @Override
    public void map(RoomCategoryEntity source, RoomCategory destination, Mapper mapper) throws MappingException {

    }
}
