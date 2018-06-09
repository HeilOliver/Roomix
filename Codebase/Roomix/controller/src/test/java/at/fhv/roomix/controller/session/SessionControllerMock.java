package at.fhv.roomix.controller.session;

import at.fhv.roomix.controller.session.model.SessionPojo;
import at.fhv.roomix.domain.session.configuration.IUiConfiguration;

/**
 * Roomix
 * at.fhv.roomix.implement.session
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

    @Override
    public void dispose() {

    }

    @Override
    public void startUp() {

    }

    @Override
    public IUiConfiguration getUiConfiguration() {
        return null;
    }
}
