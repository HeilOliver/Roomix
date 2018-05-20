package at.fhv.roomix.controller.implement.roomassignmentcontroller;

import at.fhv.roomix.domain.implement.IReservation;
import at.fhv.roomix.domain.implement.IReservationUnit;
import at.fhv.roomix.domain.implement.IRoom;
import at.fhv.roomix.domain.implement.IRoomAssignment;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This Controller is used to control the UseCase roomassignment
 */

public class RoomAssignmentController implements IRoomAssignmentController {
    private IRoomAssignmentCallback roomAssignmentCallback;

    public RoomAssignmentController(IRoomAssignmentCallback roomAssignmentCallback) {
        this.roomAssignmentCallback = roomAssignmentCallback;
    }

    /**
     * Used to create a new RoomAssignmentControllerAdapter and save it in the Database
     *
     * @param arrivalDate
     * @param departureDate
     * @param room
     * @param reservationUnit
     * @return
     */
    @Override
    public IRoomAssignment createNewRoomAssignment(Date arrivalDate, Date departureDate, IRoom room, IReservationUnit reservationUnit) {
        IRoomAssignment roomAssignment = roomAssignmentCallback.getNewRoomAssignment();
        roomAssignment.setArrivalDate(new java.sql.Date(arrivalDate.getTime()));
        roomAssignment.setDepartureDate(new java.sql.Date(departureDate.getTime()));
        roomAssignment.setRoom(room);
        roomAssignment.setReservationUnit(reservationUnit);
        roomAssignmentCallback.saveRoomAssignment(roomAssignment);
        return roomAssignment;
    }

    @Override
    public void saveRoomAssignments() {
        roomAssignmentCallback.saveAll();
    }

    @Override
    public void rollback() {
        roomAssignmentCallback.rollback();
    }

    /**
     * This Method is used to assign a Room to a Reservation automatically
     *
     * @param reservations
     */
    public void createRoomAssignmentsAutomatically(List<IReservation> reservations, boolean checkForDate) {
        for (IReservation reservation : reservations) {
            if (checkForDate) {
                Date today = new Date();
                if (!reservation.getReservationStartDate().equals(today)) {
                    return;
                }
            }
            List<IReservationUnit> reservationUnits = roomAssignmentCallback.getReservationUnitsByReservationId(reservation.getReservationID());
            for (IReservationUnit reservationUnit : reservationUnits) {
                if (reservationUnit.getAssignedRoom() != null) continue;
                LinkedList<Integer> category = new LinkedList<>();
                category.add(reservationUnit.getRoomCategory().getRoomCategoryID());
                List<IRoom> availableRooms = getRooms(reservationUnit.getStartDate(), reservationUnit.getEndDate(), category);
                if (availableRooms.size() > 0) {
                    createNewRoomAssignment(reservationUnit.getStartDate(), reservationUnit.getEndDate(), availableRooms.get(0), reservationUnit);
                    reservationUnit.setAssignedRoom(availableRooms.get(0));
                }
            }
        }
    }

    @Override
    public void deleteAssignment(IReservationUnit reservationUnit) {
        IRoom assignedRoom = reservationUnit.getAssignedRoom();
        if (assignedRoom == null) return;
        List<IRoomAssignment> roomAssignment = roomAssignmentCallback.getRoomAssignmentsByRoomAndReservationUnit();
        if (roomAssignment == null || roomAssignment.size() == 0) return;
        roomAssignmentCallback.deleteRoomAssignment(roomAssignment.get(0));
    }

    @Override
    public List<IReservationUnit> getReservationUnitsByReservationId(int reservationID) {
        return roomAssignmentCallback.getReservationUnitsByReservationId(reservationID);
    }

    /**
     * It is used to get a List with Rooms with the matching RoomCategory
     *
     * @param reservation
     * @return
     */
    @Override
    public List<IRoom> getRooms(IReservation reservation) {

        LinkedList<Integer> categories = new LinkedList<>();
        List<IReservationUnit> reservationUnits = reservation.getReservationUnits();
        reservationUnits.forEach(reservationUnit -> {
            int roomCategoryID = reservationUnit.getRoomCategory().getRoomCategoryID();
            if (!categories.contains(roomCategoryID)) {
                categories.add(roomCategoryID);
            }
        });
        List<IRoom> rooms = getRooms(reservation.getReservationStartDate(), reservation.getReservationEndDate(), categories);
        return rooms;
    }

    @Override
    public void assignRoom(IReservationUnit reservationUnit, IRoom room) {
        IRoomAssignment roomAssignment = roomAssignmentCallback.getNewRoomAssignment();
        roomAssignment.setArrivalDate(new java.sql.Date(reservationUnit.getStartDate().getTime()));
        roomAssignment.setDepartureDate(new java.sql.Date(reservationUnit.getEndDate().getTime()));
        roomAssignment.setRoom(room);
        roomAssignment.setReservationUnit(reservationUnit);
        reservationUnit.setAssignedRoom(roomAssignment.getRoom());
        roomAssignmentCallback.saveRoomAssignment(roomAssignment);
    }

    /**
     * This Method is used to get the rooms filtered with the category and the Arrival and End Date
     */
    public List<IRoom> getRooms(Date startDate, Date endDate, List<Integer> categories) {
        List<IRoom> rooms = new LinkedList<>();
        categories.forEach(category -> {
                    rooms.addAll(roomAssignmentCallback.getRoomsByRoomCategoryId(category));
                }
        );
        List<IRoomAssignment> roomAssignments = roomAssignmentCallback.getRoomAssignmentsByDate(startDate, endDate);

        for (IRoomAssignment roomAssignment : roomAssignments) {
            for (int i = rooms.size() - 1; i >= 0; i--) {
                if (roomAssignment.getRoom().getRoomID() == rooms.get(i).getRoomID()) {
                    rooms.remove(i);
                }
            }
        }
        return rooms;
    }
}
