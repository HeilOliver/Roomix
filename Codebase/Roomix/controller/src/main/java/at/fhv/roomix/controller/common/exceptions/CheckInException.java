package at.fhv.roomix.controller.common.exceptions;

import at.fhv.roomix.controller.common.exceptions.FaultException;

/**
 * Roomix
 * at.fhv.roomix.implement.stay
 * CheckInException
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CheckInException extends FaultException {

    public CheckInException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckInException(Throwable cause) {
        super(cause);
    }

    protected CheckInException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckInException() {
        super();
    }

    public CheckInException(String message) {
        super(message);
    }
}
