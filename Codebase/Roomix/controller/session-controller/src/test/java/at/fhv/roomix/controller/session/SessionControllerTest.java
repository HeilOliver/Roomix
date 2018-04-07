package at.fhv.roomix.controller.session;

import at.fhv.roomix.controller.session.exception.AuthenticationFaultException;
import at.fhv.roomix.controller.session.model.SessionPojo;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionControllerTest
 * 07/04/2018 Oliver
 * <p>
 * Enter Description here
 */
class SessionControllerTest {
    static SessionDomainMock mock;
    static SessionController controller;

    @BeforeAll
    static void init() {
        mock = new SessionDomainMock();
        SessionFactory.inject(mock);
        controller = new SessionController();
    }

    @Test
    void getSession_invalidUserPassword() {
        assertThrows(AuthenticationFaultException.class,
                ()-> controller.getSession("Test", "Test")
        , "Invalid User and Password");

        assertThrows(AuthenticationFaultException.class,
                ()-> controller.getSession("Test", "")
        , "Invalid Password");

        assertThrows(AuthenticationFaultException.class,
                ()-> controller.getSession("", "Test")
        , "Invalid User");

        assertThrows(AuthenticationFaultException.class,
                ()-> controller.getSession(null, null)
                , "Invalid User and Password");

        assertThrows(AuthenticationFaultException.class,
                ()-> controller.getSession("Test", null)
                , "Invalid Password");

        assertThrows(AuthenticationFaultException.class,
                ()-> controller.getSession(null, "Test")
                , "Invalid User");
    }

    @Test
    void getSession_ok() throws AuthenticationFaultException {
        SessionPojo session
                = controller.getSession("OK", "OK");

        assertEquals("OK", session.getName());
        assertEquals(100L, session.getSessionId());
    }

    @Test
    void closeSession_ok() throws AuthenticationFaultException {
        SessionPojo session
                = controller.getSession("OK", "OK");

        mock.clear();
        controller.closeSession(session.getSessionId());
        assertTrue(mock.isSessionClosed);
    }

    @Test
    void closeSession_invalidId() {
        mock.clear();
        controller.closeSession(123812798L);
        assertTrue(mock.isSessionClosed);
    }
}