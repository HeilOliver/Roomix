package at.fhv.roomix.controller;

import at.fhv.roomix.controller.session.ISessionController;
import at.fhv.roomix.controller.session.exception.AuthenticationFaultException;
import at.fhv.roomix.controller.session.model.SessionPojo;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.InvalidUserPasswordCombination;
import at.fhv.roomix.domain.session.configuration.IUiConfiguration;
import at.fhv.roomix.domain.session.model.RoomixSession;
import at.fhv.roomix.domain.session.roll.IRoomixRoll;

/**
 * Roomix
 * at.fhv.roomix.controller
 * ${FILE_NAME}
 * 07/06/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class SessionMock implements ISessionDomain {


    @Override
    public RoomixSession getSession(String username, String password) throws InvalidUserPasswordCombination {
        return null;
    }

    @Override
    public boolean isValid(long sessionId) {
        return sessionId == 1234;
    }

    @Override
    public boolean isValidFor(long sessionId, IRoomixRoll roll) {
        return true;
    }

    @Override
    public void closeSession(long sessionId) {

    }
}
