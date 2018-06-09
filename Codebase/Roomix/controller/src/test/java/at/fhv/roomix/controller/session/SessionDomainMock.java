package at.fhv.roomix.controller.session;

import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.InvalidUserPasswordCombination;
import at.fhv.roomix.domain.session.model.RoomixSession;
import at.fhv.roomix.domain.session.roll.IRoomixRoll;

/**
 * Roomix
 * at.fhv.roomix.implement.session
 * SessionDomainMock
 * 07/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class SessionDomainMock implements ISessionDomain {

    boolean isSessionClosed;

    @Override
    public RoomixSession getSession(String username, String password) throws InvalidUserPasswordCombination {
        if (username == null || password == null)
            throw new InvalidUserPasswordCombination();

        if (!username.equals("OK") || !password.equals("OK"))
            throw new InvalidUserPasswordCombination();

        return new RoomixSession(100L, username, null);
    }

    @Override
    public boolean isValid(long sessionId) {
        return false;
    }

    @Override
    public boolean isValidFor(long sessionId, IRoomixRoll roll) {
        return false;
    }

    @Override
    public void closeSession(long sessionId) {
        isSessionClosed = true;
    }

    void clear() {
        isSessionClosed = false;
    }
}
