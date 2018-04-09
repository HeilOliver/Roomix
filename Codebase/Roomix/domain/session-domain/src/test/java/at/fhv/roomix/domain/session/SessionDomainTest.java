package at.fhv.roomix.domain.session;

import at.fhv.roomix.domain.session.model.RoomixSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.domain.session
 * SessionDomainTest
 * 05/04/2018 Oliver
 * <p>
 * Enter Description here
 */
class SessionDomainTest {

    private static final String username = "Max";
    private static final String password = "MaxsPassword";
    private static SessionDomain sessionDomain;

    @BeforeEach
    void init() {
        sessionDomain = new SessionDomain();
    }

    @Test
    void getSession() throws InvalidUserPasswordCombination {
        RoomixSession session = sessionDomain.getSession(username, password);
        String getUsername = session.getUsername();
        assertEquals(username, getUsername);
        assertTrue(session.isValid());
        assertNotEquals(0, session.getSessionId());
    }

    @Test
    void validSession() throws InvalidUserPasswordCombination {
        RoomixSession session0
                = sessionDomain.getSession(username, password);
        RoomixSession session1
                = sessionDomain.getSession(username, password);

        assertNotEquals(session0, session1);
        assertFalse(session0.isValid());
        assertTrue(session1.isValid());

        assertNotEquals(session0.getSessionId(), session1.getSessionId());
    }

    @Test
    void requestRoll() throws InvalidUserPasswordCombination {
        RoomixSession session
                = sessionDomain.getSession(username, password);
        boolean valid = sessionDomain.isValidFor(session.getSessionId(), RollMock.getInstance());
        assertTrue(valid);
    }

    @Test
    void invalidUserPassword() {
        assertThrows(InvalidUserPasswordCombination.class,
                () -> sessionDomain.getSession(null, password));
        assertThrows(InvalidUserPasswordCombination.class,
                () -> sessionDomain.getSession(username, null));
        assertThrows(InvalidUserPasswordCombination.class,
                () -> sessionDomain.getSession(null, null));
    }
}