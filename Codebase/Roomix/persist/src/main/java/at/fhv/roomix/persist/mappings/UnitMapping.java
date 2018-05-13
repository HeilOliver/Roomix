package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ReservationUnitEntity;
import at.fhv.roomix.persist.models.RoomCategoryEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * UnitMapping
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class UnitMapping implements MapType<ReservationUnit, ReservationUnitEntity> {

    @Override
    public void map(ReservationUnit source, ReservationUnitEntity destination, Mapper mapper) throws MappingException {
        try {
            RoomCategoryEntity categoryEntity
                    = RoomCategoryFactory.getInstance().get(source.getCategory().getId());
            destination.setRoomCategory(categoryEntity);
        } catch (PersistLoadException e) {
            throw new MappingException("Mapping Error - " + e.getMessage(), e);
        }

        destination.setArrivalTime(source.getArrivalTime());
        destination.setStartDate(source.getStartDate());
        destination.setEndDate(source.getEndDate());
        destination.setStatus(source.getStatus().toString());
    }
}
