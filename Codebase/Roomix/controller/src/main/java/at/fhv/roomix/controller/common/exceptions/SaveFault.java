package at.fhv.roomix.controller.common.exceptions;

/**
 * Roomix
 * at.fhv.roomix.implement.common.exceptions
 * SaveFault
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class SaveFault extends FaultException {
    public SaveFault(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveFault(Throwable cause) {
        super(cause);
    }

    protected SaveFault(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SaveFault() {
        super();
    }

    public SaveFault(String message) {
        super(message);
    }
}
