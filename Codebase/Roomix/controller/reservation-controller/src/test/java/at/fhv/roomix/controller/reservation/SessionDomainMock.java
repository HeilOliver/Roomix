package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.domain.session.ISessionDomain;
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
        return true;
    }

    @Override
    public boolean isValidFor(long sessionId, IRoomixRoll roll) {
        return true;
    }

    @Override
    public void closeSession(long sessionId) {

    }
}
