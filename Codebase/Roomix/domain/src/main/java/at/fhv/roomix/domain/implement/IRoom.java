package at.fhv.roomix.domain.implement;

import java.util.Observer;

public interface IRoom {
    int getRoomID();
    String getRoomCategoryDescription();
    String getStatus();
    void addObserver(Observer o);
}
