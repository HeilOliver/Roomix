package at.fhv.roomix.controller.implement.adapter;

import at.fhv.roomix.controller.implement.roomassignmentcontroller.IRoomAssignmentCallback;
import at.fhv.roomix.domain.implement.IReservationUnit;
import at.fhv.roomix.domain.implement.IRoom;
import at.fhv.roomix.domain.implement.IRoomAssignment;
import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.reservation.RoomAssignment;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.builder.accessbuilder.ReservationBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.RoomAssignmentBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistSaveException;
import at.fhv.roomix.persist.exception.PersistStateException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class RoomAssignmentControllerAdapter implements IRoomAssignmentCallback {

    @Override
    public IRoomAssignment getNewRoomAssignment() {
        return new RoomAssignmentAdapter(new RoomAssignment());
    }

    @Override
    public void saveRoomAssignment(IRoomAssignment roomAssignment) {
        if (!(roomAssignment instanceof RoomAssignmentAdapter)) throw new IllegalArgumentException();
        try {
            RoomAssignmentBuilder.save(((RoomAssignmentAdapter) roomAssignment).getAssignment());
        } catch (BuilderUpdateException e) {
            // TODO LOG HERE
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll() {
        try {
            EntityFactory.commitAll();
        } catch (PersistStateException | PersistSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IReservationUnit> getReservationUnitsByReservationId(int reservationID) {
        try {
            Reservation reservation = ReservationBuilder.get(reservationID);
            Collection<ReservationUnit> units = reservation.getUnits();
            return units.stream().map(ReservationUnitAdapter::new).collect(Collectors.toList());
        } catch (BuilderLoadException e) {
            // TODO LOG Here
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<? extends IRoom> getRoomsByRoomCategoryId(Integer category) {
        try {
            RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(category);
            return roomCategory.getRooms().stream().map(RoomAdapter::new).collect(Collectors.toSet());
        } catch (BuilderLoadException e) {
            // TODO LOG Here
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    @Override
    public List<IRoomAssignment> getRoomAssignmentsByDate(Date startDate, Date endDate) {
        Collection<RoomAssignment> all = null;
        try {
            all = RoomAssignmentBuilder.getAll();
        } catch (BuilderLoadException e) {
            // TODO LOG HERE
            e.printStackTrace();
            return new ArrayList<>();
        }
        LocalDate arrivalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate departureDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        assert all != null;
        return all.stream()
                .filter(a -> a.getArrivalDate().isAfter(arrivalDate))
                .filter(a -> departureDate.isAfter(a.getDepartureDate()))
                .map(RoomAssignmentAdapter::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<IRoomAssignment> getRoomAssignmentsByRoomAndReservationUnit() {
        return new ArrayList<>();
    }

    @Override
    public void deleteRoomAssignment(IRoomAssignment roomAssignment) {
        if (!(roomAssignment instanceof RoomAssignmentAdapter)) throw new IllegalArgumentException();
        try {
            RoomAssignmentBuilder.delete(((RoomAssignmentAdapter) roomAssignment).getAssignment());
        } catch (BuilderUpdateException e) {
            // TODO LOG HERE
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        EntityFactory.stashChanges();
    }
}
