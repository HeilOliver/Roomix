package at.fhv.roomix.controller.session;

import at.fhv.roomix.controller.session.exception.AuthenticationFaultException;
import at.fhv.roomix.controller.session.model.SessionPojo;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * ISessionController
 * 22.03.2018 sge
 * <p>
 * The Interface for the SessionController
 */
public interface ISessionController {

    SessionPojo getSession(String username, String password) throws AuthenticationFaultException;

    void closeSession(long SessionId);

    void dispose();
}
