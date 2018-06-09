package at.fhv.roomix.controller.model;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.implement.model
 * RoomPojo
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomPojo {
    private LocalDate startDate;
    private LocalDate endDate;
    private String roomNo;

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getRoomNo() {
        return roomNo;
    }
}
