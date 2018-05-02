package at.fhv.roomix.persist.exception;

/**
 * Roomix
 * at.fhv.roomix.persist.exception
 * PersistStateException
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersistStateException extends PersistException {
    public PersistStateException() {
    }

    public PersistStateException(String message) {
        super(message);
    }

    public PersistStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistStateException(Throwable cause) {
        super(cause);
    }

    public PersistStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
