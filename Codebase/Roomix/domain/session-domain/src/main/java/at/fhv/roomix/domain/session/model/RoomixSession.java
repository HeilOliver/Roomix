package at.fhv.roomix.domain.session.model;

/**
 * Roomix
 * at.fhv.roomix.domain.session.model
 * RoomixSession
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class RoomixSession {
    private long sessionId;
    private String username;
    private boolean valid;
    private RoomixUser user;


    public RoomixSession(long sessionId, String username, RoomixUser user) {
        this.sessionId = sessionId;
        this.username = username;
        this.valid = true;
        this.user = user;
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

    public boolean isValid() {
        return valid;
    }

    public void setInValid() {
        valid = false;
    }

    public RoomixUser getUser() {
        return user;
    }
}
