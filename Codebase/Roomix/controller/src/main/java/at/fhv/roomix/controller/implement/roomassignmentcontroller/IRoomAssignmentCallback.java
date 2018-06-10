package at.fhv.roomix.controller.implement.roomassignmentcontroller;

import at.fhv.roomix.domain.implement.IReservationUnit;
import at.fhv.roomix.domain.implement.IRoom;
import at.fhv.roomix.domain.implement.IRoomAssignment;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRoomAssignmentCallback {
    IRoomAssignment getNewRoomAssignment();

    void saveRoomAssignment(IRoomAssignment roomAssignment);

    void saveAll();

    List<IReservationUnit> getReservationUnitsByReservationId(int reservationID);

    Collection<? extends IRoom> getRoomsByRoomCategoryId(Integer category);

    List<IRoomAssignment> getRoomAssignmentsByDate(Date startDate, Date endDate);

    List<IRoomAssignment> getRoomAssignmentsByRoomAndReservationUnit(IRoom assignedRoom, IReservationUnit reservationUnit);

    void deleteRoomAssignment(IRoomAssignment roomAssignment);

    void rollback();
}
