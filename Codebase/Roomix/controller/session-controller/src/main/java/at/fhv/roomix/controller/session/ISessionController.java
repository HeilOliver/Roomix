package at.fhv.roomix.controller.session;

import at.fhv.roomix.controller.session.model.SessionPojo;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * ISessionController
 * 22.03.2018 sge
 *
 * The Interface for the SessionController
 */
public interface ISessionController {

    SessionPojo getSession(String username, String password);

    void closeSession(long SessionId);

}
