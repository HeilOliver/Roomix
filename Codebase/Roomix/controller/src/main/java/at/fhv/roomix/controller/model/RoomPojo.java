package at.fhv.roomix.controller.model;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.controller.model
 * RoomPojo
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomPojo {
    private LocalDate startDate;
    private LocalDate endDate;
    private String roomNo;

    public RoomPojo(LocalDate startDate, LocalDate endDate, String roomNo) {
        this.startDate = startDate;
        this.endDate = endDate;
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
