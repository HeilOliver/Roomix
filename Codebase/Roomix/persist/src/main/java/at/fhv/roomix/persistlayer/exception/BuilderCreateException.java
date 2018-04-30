package at.fhv.roomix.persistlayer.exception;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.exception
 * BuilderCreateException
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class BuilderCreateException extends BuilderException {
    public BuilderCreateException() {
    }

    public BuilderCreateException(String message) {
        super(message);
    }

    public BuilderCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuilderCreateException(Throwable cause) {
        super(cause);
    }

    public BuilderCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
