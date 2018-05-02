package at.fhv.roomix.domain.session;

import at.fhv.roomix.domain.session.roll.IRoomixRoll;

/**
 * Roomix
 * at.fhv.roomix.domain.session
 * RollMock
 * 05/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RollMock implements IRoomixRoll {
    private static IRoomixRoll instance = new RollMock();

    public static IRoomixRoll getInstance() {
        return instance;
    }
}
