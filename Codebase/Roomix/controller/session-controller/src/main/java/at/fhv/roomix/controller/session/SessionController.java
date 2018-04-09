package at.fhv.roomix.controller.session;

import at.fhv.roomix.controller.session.exception.AuthenticationFaultException;
import at.fhv.roomix.controller.session.model.SessionPojo;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.InvalidUserPasswordCombination;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.domain.session.model.RoomixSession;
import org.apache.log4j.Logger;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionController
 * 22.03.2018 sge
 * <p>
 * The Implementation for the SessionController itself
 */
class SessionController implements ISessionController {
    private static Logger logger = Logger.getLogger(SessionController.class);

    private static SessionPojo toSessionPojo(RoomixSession session) {
        SessionPojo sessionPojo = new SessionPojo();
        sessionPojo.setName(session.getUsername());
        sessionPojo.setSessionId(session.getSessionId());
        return sessionPojo;
    }

    @Override
    public SessionPojo getSession(String username, String password) throws AuthenticationFaultException {
        ISessionDomain instance = SessionFactory.getInstance();
        RoomixSession session;
        try {
            session = instance.getSession(username, password);
        } catch (InvalidUserPasswordCombination e) {
            logger.info("Login attempt failed - " + username);
            throw new AuthenticationFaultException();
        }
        return toSessionPojo(session);
    }

    @Override
    public void closeSession(long sessionId) {
        ISessionDomain instance = SessionFactory.getInstance();
        instance.closeSession(sessionId);
    }
}
