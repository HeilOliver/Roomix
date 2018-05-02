package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.common.Proxy;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.persist.dataaccess.factory.ReservationUnitFactory;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ReservationUnitEntity;
import at.fhv.roomix.persist.models.RoomCategoryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * ReservationUnitBuilder
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnitBuilder {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ReservationUnit, ReservationUnitEntity>() {
            @Override
            protected void configure() {
                skip().setRoomCategory(null);
                skip().setCancellation(null);
                skip().setReservation(null);
                skip().setInvoicePositions(null);
                skip().setRoomAssignments(null);
            }
        });
        mapper.addMappings(new PropertyMap<ReservationUnitEntity, ReservationUnit>() {
            @Override
            protected void configure() {
                skip().setReservation(null);
                skip().setCategory(null);
                skip().setArrangements(null);
            }
        });
    }

    static ReservationUnitEntity updateUnitUC(ReservationUnit unit) throws RuntimeException {
        try {
            return updateUnit(unit);
        } catch (BuilderUpdateException | PersistLoadException e) {
            throw new RuntimeException(e);
        }
    }

    public static Collection<ReservationUnit> getAll() throws BuilderLoadException{
        List<ReservationUnitEntity> all;
        try {
            all = ReservationUnitFactory.getInstance().getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }

        HashSet<ReservationUnit> result = new HashSet<>();
        for (ReservationUnitEntity entity : all) {
            result.add(fromEntity(entity));
        }
        return result;
    }

    private static ReservationUnitEntity updateUnit(ReservationUnit unit) throws BuilderUpdateException, PersistLoadException {
        ReservationUnitEntity entity;
        try {
            entity = unit.getId() == 0 ? new ReservationUnitEntity() : ReservationUnitFactory.getInstance().get(unit.getId());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("", e);
        }

        RoomCategoryEntity roomCategoryEntity
                = RoomCategoryFactory.getInstance().get(unit.getCategory().getId());

        entity.setRoomCategory(roomCategoryEntity);
        mapper.map(unit, entity);

        ReservationUnitFactory.getInstance().saveOrUpdate(entity);
        return entity;
    }

    private static ReservationUnit fromEntity(ReservationUnitEntity entity) throws BuilderLoadException {
        ReservationUnit unit = mapper.map(entity, ReservationUnit.class);

        unit.setCategory(RoomCategoryBuilder
                .getRoomCategory(entity.getRoomCategory().getRoomCategoryId()));

        unit.setReservation(
                new Proxy<>(() -> ReservationBuilder.get(entity.getReservation().getReservationId())));

        return unit;
    }

    private static ReservationUnit get(int id) throws BuilderLoadException {
        ReservationUnitEntity entity;
        try {
            entity = ReservationUnitFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }

        return fromEntity(entity);
    }

    static ReservationUnit getUC(int id) throws RuntimeException {
        try {
            return get(id);
        } catch (BuilderLoadException e) {
            throw new RuntimeException(e);
        }
    }
}
