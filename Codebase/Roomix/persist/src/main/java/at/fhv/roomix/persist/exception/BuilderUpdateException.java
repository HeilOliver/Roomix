package at.fhv.roomix.persist.exception;

/**
 * Roomix
 * at.fhv.roomix.persist.exception
 * BuilderUpdateException
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class BuilderUpdateException extends BuilderException {
    public BuilderUpdateException() {
    }

    public BuilderUpdateException(String message) {
        super(message);
    }

    public BuilderUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuilderUpdateException(Throwable cause) {
        super(cause);
    }

    public BuilderUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
