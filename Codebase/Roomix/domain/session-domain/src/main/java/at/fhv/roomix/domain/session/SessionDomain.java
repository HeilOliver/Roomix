package at.fhv.roomix.domain.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private Map<RoomixUser, RoomixSession> knownSession = new HashMap<>();

    private long generateSessionId() {
        AtomicBoolean found = new AtomicBoolean(false);
        long nextId = 0;
        while (!found.get()) {
            long l = random.nextLong();
            nextId = Math.abs(l);

            if (nextId == 0) continue;

            found.set(true);

            long finalNextId = nextId;
            knownSession.forEach((k, v) -> {
                if (v.getSessionId() == finalNextId) found.set(false);
            });
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
        RoomixSession session = new RoomixSession(sessionId, username, true);

        if (knownSession.containsKey(dummyUser))
            knownSession.get(dummyUser).setInValid();

        knownSession.put(dummyUser, session);
        return session;
    }

    @Override
    public boolean isValid(long sessionId) {
        Collection<RoomixSession> values = knownSession.values();

        for (RoomixSession value : values) {
            if (value.getSessionId() != sessionId) continue;

            return value.isValid();
        }
        return false;
    }

    @Override
    public boolean isValidFor(long sessionId, IRoomixRoll roll) {
        if (!isValid(sessionId)) return false;
        // More Logic here
        return true;
    }
}
