package at.fhv.roomix.persist.exeption;

/**
 * Roomix
 * at.fhv.roomix.persist.exeption
 * PersistSaveException
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class PersistSaveException extends Exception {

    public PersistSaveException() {
        super();
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

    protected PersistSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
