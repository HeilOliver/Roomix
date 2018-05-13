package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.domain.common.Proxy;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.persist.dataaccess.factory.PersonFactory;
import at.fhv.roomix.persist.dataaccess.factory.ReservationUnitFactory;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.mappings.RoomMapping;
import at.fhv.roomix.persist.mappings.UnitMapping;
import at.fhv.roomix.persist.models.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDate;
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
    private static final Mapper _mapper = Mapper.getInstance();

    static {
        _mapper.addMapType(new RoomMapping(), RoomEntity.class, Room.class);
        _mapper.addMapType(new UnitMapping(), ReservationUnit.class, ReservationUnitEntity.class);
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
        } catch (BuilderUpdateException e) {
            throw new RuntimeException(e);
        }
    }

    public static Collection<ReservationUnit> getAll() throws BuilderLoadException {
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

    private static ReservationUnitEntity updateUnit(ReservationUnit unit) throws BuilderUpdateException {
        ReservationUnitEntity entity;
            try {
                entity = unit.getId() == 0 ? new ReservationUnitEntity() : ReservationUnitFactory.getInstance().get(unit.getId());

                RoomCategoryEntity roomCategoryEntity
                        = RoomCategoryFactory.getInstance().get(unit.getCategory().getId());
                entity.setRoomCategory(roomCategoryEntity);
            } catch (PersistLoadException e) {
            throw new BuilderUpdateException("", e);
        }

        _mapper.map(unit, entity);

        Collection<Person> guests = unit.getGuests();
        for (Person guest : guests) {
            try {
                PersonEntity personEntity = PersonFactory.getInstance().get(guest.getId());
                personEntity.getGuestsAtUnit().add(entity);
                entity.getPersons().add(personEntity);
            } catch (PersistLoadException e) {
                throw new BuilderUpdateException();
            }
        }

        HashSet<Room> rooms = new HashSet<>(unit.getAssignedRooms().values());
        for (Room room : rooms) {
            RoomBuilder.updateRoom(room);
        }

        ReservationUnitFactory.getInstance().saveOrUpdate(entity);

        return entity;
    }

    private static ReservationUnit fromEntity(ReservationUnitEntity entity) throws BuilderLoadException {
        ReservationUnit unit = mapper.map(entity, ReservationUnit.class);

        unit.setCategory(RoomCategoryBuilder
                .getRoomCategory(entity.getRoomCategory().getRoomCategoryId()));

        unit.setReservation(
                new Proxy<>(() -> ReservationBuilder.get(entity.getReservation().getReservationId())));

        if (!entity.getRoomAssignments().isEmpty()) {
            Collection<RoomAssignmentEntity> assignments = entity.getRoomAssignments();
            for (RoomAssignmentEntity assignment : assignments) {
                RoomEntity roomEntity = assignment.getRoom();
                Room room = _mapper.map(roomEntity, Room.class);

                LocalDate currDate = assignment.getArrivalDate().toLocalDate();
                LocalDate endDate = assignment.getDepartureDate().toLocalDate();

                do {
                    unit.getAssignedRooms().put(currDate, room);
                    currDate = currDate.plusDays(1);
                } while (currDate.isBefore(endDate));
            }
        }
        unit.setStatus(ReservationUnit.UnitStatus.valueOf(entity.getStatus()));
        return unit;
    }

    public static ReservationUnit get(int id) throws BuilderLoadException {
        if (id <= 0) return new ReservationUnit();
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

    public static void update(ReservationUnit unit) throws BuilderUpdateException {
        updateUnit(unit);
    }
}
