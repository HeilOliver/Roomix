package at.fhv.roomix.common;

/**
 * Roomix
 * at.fhv.roomix.common
 * MappingException
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class MappingException extends RuntimeException {
    public MappingException() {
    }

    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }

    public MappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
