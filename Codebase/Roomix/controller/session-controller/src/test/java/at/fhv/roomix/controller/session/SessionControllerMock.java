package at.fhv.roomix.controller.session;

import at.fhv.roomix.controller.session.model.SessionPojo;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionControllerMock
 * 22.03.2018 sge
 * <p>
 * Mock fpr ISessionController
 */
public class SessionControllerMock implements ISessionController {


    @Override
    public SessionPojo getSession(String username, String password) {
        return null;
    }

    @Override
    public void closeSession(long SessionId) {

    }
}
