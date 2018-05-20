package at.fhv.roomix.controller.implement.roomassignmentcontroller;

import at.fhv.roomix.domain.implement.IReservation;
import at.fhv.roomix.domain.implement.IReservationUnit;
import at.fhv.roomix.domain.implement.IRoomAssignment;
import at.fhv.roomix.domain.implement.IRoom;
import java.util.Date;
import java.util.List;

public interface IRoomAssignmentController {
    IRoomAssignment createNewRoomAssignment(Date arrivalDate, Date departureDate, IRoom room, IReservationUnit reservationUnit);
    List<IRoom> getRooms(IReservation reservation);
    void assignRoom(IReservationUnit selectedReservationUnit, IRoom selectedRoom);
    void saveRoomAssignments();
    void rollback();
    void createRoomAssignmentsAutomatically(List<IReservation> reservations, boolean checkForDate);
    void deleteAssignment(IReservationUnit selectedItem);
    List<IReservationUnit> getReservationUnitsByReservationId(int reservationID);
}
