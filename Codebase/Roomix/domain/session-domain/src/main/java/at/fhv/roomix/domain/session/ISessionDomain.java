package at.fhv.roomix.domain.session;

import at.fhv.roomix.domain.session.model.RoomixSession;
import at.fhv.roomix.domain.session.roll.IRoomixRoll;

/**
 * Roomix
 * at.fhv.roomix.domain.guest
 * ISessionDomain
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public interface ISessionDomain {

    RoomixSession getSession(String username, String password);

    boolean isValid(long sessionId);

    boolean isValidFor(long sessionId, IRoomixRoll roll);

}
