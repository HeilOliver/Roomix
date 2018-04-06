package at.fhv.roomix.domain.session;

import at.fhv.roomix.domain.session.model.RoomixSession;
import at.fhv.roomix.domain.session.roll.IRoomixRoll;

/**
 * Roomix
 * at.fhv.roomix.domain.session
 * SessionDomainMock
 * 05/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class SessionDomainMock implements ISessionDomain {
    @Override
    public RoomixSession getSession(String username, String password) {
        return null;
    }

    @Override
    public boolean isValid(long sessionId) {
        return false;
    }

    @Override
    public boolean isValidFor(long sessionId, IRoomixRoll roll) {
        return false;
    }
}
