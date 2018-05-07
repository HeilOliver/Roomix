package at.fhv.roomix.persist.exception;

/**
 * Roomix
 * at.fhv.roomix.persist
 * PersistSaveException
 * 04/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersistSaveException extends PersistException {
    public PersistSaveException() {
    }

    public PersistSaveException(String message) {
        super(message);
    }

    public PersistSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistSaveException(Throwable cause) {
        super(cause);
    }

    public PersistSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
