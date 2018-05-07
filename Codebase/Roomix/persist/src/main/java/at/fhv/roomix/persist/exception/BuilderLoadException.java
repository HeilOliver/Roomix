package at.fhv.roomix.persist.exception;

/**
 * Roomix
 * at.fhv.roomix.persist.exception
 * BuilderLoadException
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class BuilderLoadException extends BuilderException {
    public BuilderLoadException() {
    }

    public BuilderLoadException(String message) {
        super(message);
    }

    public BuilderLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuilderLoadException(Throwable cause) {
        super(cause);
    }

    public BuilderLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
