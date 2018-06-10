package at.fhv.roomix.controller.common.exceptions;

/**
 * Roomix
 * at.fhv.roomix.implement.common.exceptions
 * GetFault
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class GetFault extends FaultException {
    public GetFault() {
    }

    public GetFault(String message) {
        super(message);
    }

    public GetFault(String message, Throwable cause) {
        super(message, cause);
    }

    public GetFault(Throwable cause) {
        super(cause);
    }

    protected GetFault(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
