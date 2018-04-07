package at.fhv.roomix.controller.session;

import at.fhv.roomix.controller.session.model.SessionPojo;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionController
 * 22.03.2018 sge
 *
 * The Implementation for the SessionController itself
 */
class SessionController implements ISessionController {


    @Override
    public SessionPojo getSession(String username, String password) {
        SessionPojo pojo = new SessionPojo();
        pojo.setName("Max");
        pojo.setSessionId(9090910293091301L);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pojo;
    }

    @Override
    public void closeSession(long SessionId) {

    }
}
