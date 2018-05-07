package at.fhv.roomix.persist.exception;

/**
 * Roomix
 * at.fhv.roomix.persist.exception
 * PersistException
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersistException extends Exception {
    public PersistException() {
    }

    public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }

    public PersistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
