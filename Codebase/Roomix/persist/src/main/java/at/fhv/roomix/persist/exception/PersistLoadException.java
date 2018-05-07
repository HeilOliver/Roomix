package at.fhv.roomix.persist.exception;

/**
 * Roomix
 * at.fhv.roomix.persist
 * PersistLoadException
 * 04/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersistLoadException extends PersistException {
    public PersistLoadException() {
    }

    public PersistLoadException(String message) {
        super(message);
    }

    public PersistLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistLoadException(Throwable cause) {
        super(cause);
    }

    public PersistLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
