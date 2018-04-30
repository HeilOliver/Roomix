package at.fhv.roomix.persistlayer.exception;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.exception
 * BuilderException
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class BuilderException extends PersistException {
    public BuilderException() {
    }

    public BuilderException(String message) {
        super(message);
    }

    public BuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuilderException(Throwable cause) {
        super(cause);
    }

    public BuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
