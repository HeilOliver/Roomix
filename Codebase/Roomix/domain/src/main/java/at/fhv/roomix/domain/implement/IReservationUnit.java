package at.fhv.roomix.domain.implement;

import java.util.Date;
import java.util.Observer;

public interface IReservationUnit {
    int getReservationUnitID();
    IReservation getReservation();
    IRoomCategory getRoomCategory();
    Date getStartDate();
    Date getEndDate();
    String getRoomID();
    String getRoomStatus();
    int getReservationId();
    String getArrivalTime();
    void addObserver(Observer o);
    IRoom getAssignedRoom();
    void setAssignedRoom(IRoom room);
}
