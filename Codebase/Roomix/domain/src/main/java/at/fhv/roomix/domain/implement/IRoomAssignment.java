package at.fhv.roomix.domain.implement;

import java.sql.Date;

public interface IRoomAssignment {
    int getRoomAssignmentId();

    String getArrivalDate();

    String getDepartureDate();

    int getRoomId();

    int getReservationUnitId();

    void setArrivalDate(Date date);

    void setDepartureDate(Date date);

    void setReservationUnit(IReservationUnit reservationUnit);

    IRoom getRoom();

    void setRoom(IRoom room);
}
