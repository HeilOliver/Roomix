package at.fhv.roomix.persist.exeption;

/**
 * Roomix
 * at.fhv.roomix.persist.exeption
 * PersistLoadException
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class PersistLoadException extends Exception {
    public PersistLoadException() {
        super();
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

    protected PersistLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
