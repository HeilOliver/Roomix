package at.fhv.roomix.controller.model;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.controller.stay.model
 * CheckInStatus
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CheckInReply {
    private String roomNo;
    private LocalDate roomChange;
    private Reply replyMessage;

    public CheckInReply(String roomNo, LocalDate roomChange, Reply replyMessage) {
        this.roomNo = roomNo;
        this.roomChange = roomChange;
        this.replyMessage = replyMessage;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public LocalDate getRoomChange() {
        return roomChange;
    }

    public Reply getReplyMessage() {
        return replyMessage;
    }

    public enum Reply{
        DOUBLE_OCCUPATION,
        DIRTY,
        OK
    }
}
