package at.fhv.roomix.controller.session.model;

/**
 * Roomix
 * at.fhv.roomix.controller.session.model
 * SessionPojo
 * 22.03.2018 sge
 *
 * The Guest Pojo which is been used later on
 */
public class SessionPojo {
    private long sessionId;
    private String name;

    public long getSessionId() {
        return sessionId;
    }

    public String getName() {
        return name;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
