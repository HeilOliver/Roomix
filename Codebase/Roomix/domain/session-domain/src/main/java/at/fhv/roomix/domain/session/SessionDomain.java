package at.fhv.roomix.domain.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import at.fhv.roomix.domain.session.model.RoomixSession;
import at.fhv.roomix.domain.session.model.RoomixUser;
import at.fhv.roomix.domain.session.roll.IRoomixRoll;

/**
 * Roomix
 * at.fhv.roomix.domain.guest
 * SessionDomain
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class SessionDomain implements ISessionDomain {
    private Random random = new Random();
    private Map<Long, RoomixUser> knownUser = new HashMap<>();

    private long generateSessionId() {
        boolean found = false;
        long nextId = 0;
        while (!found) {
            long l = random.nextLong();
            nextId = Math.abs(l);

            if (nextId == 0) continue;
            if (knownUser.containsKey(nextId)) continue;

            found = true;
        }
        return nextId;
    }

    private static final RoomixUser dummyUser = new RoomixUser();

    @Override
    public RoomixSession getSession(String username, String password) {
        if (username == null || password == null) {
            return new RoomixSession(0, "None", false);
        }
        long sessionId = generateSessionId();
        knownUser.put(sessionId, dummyUser);
        return new RoomixSession(generateSessionId(), "Hi", true);
    }

    @Override
    public boolean isValid(long sessionId) {
        return knownUser.containsKey(sessionId);
    }

    @Override
    public boolean isValidFor(long sessionId, IRoomixRoll roll) {
        if (!isValid(sessionId)) return false;
        // More Logic here
        return true;
    }
}
