package at.fhv.roomix.domain.session;

import at.fhv.roomix.domain.session.model.RoomixSession;
import at.fhv.roomix.domain.session.model.RoomixUser;
import at.fhv.roomix.domain.session.roll.IRoomixRoll;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private Map<Long, RoomixSession> knownSession = new HashMap<>();

    private long generateSessionId() {
        // TODO Refactor to new HashMap (Session Id is now Key)
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
    public RoomixSession getSession(String username, String password) throws InvalidUserPasswordCombination  {
        // TODO For Integration Testing
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignore) {
        }

        if (username == null || password == null)
            throw new InvalidUserPasswordCombination();

        if (!password.equals("0000"))
            throw new InvalidUserPasswordCombination();

        long sessionId = generateSessionId();
        RoomixSession session = new RoomixSession(sessionId, username, dummyUser);

        knownSession.put(sessionId, session);
        return session;
    }

    @Override
    public boolean isValid(long sessionId) {
        if (!knownSession.containsKey(sessionId))
            return false;

        return knownSession.get(sessionId).isValid();
    }

    @Override
    public boolean isValidFor(long sessionId, IRoomixRoll roll) {
        if (!isValid(sessionId)) return false;
        // More Logic here
        return true;
    }

    @Override
    public void closeSession(long sessionId) {
        knownSession.remove(sessionId);
    }
}
