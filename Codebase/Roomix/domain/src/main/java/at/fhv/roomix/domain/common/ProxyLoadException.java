package at.fhv.roomix.domain.common;

/**
 * Roomix
 * at.fhv.roomix.domain.common
 * ProxyLoadException
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ProxyLoadException extends RuntimeException {

    public ProxyLoadException() {
    }

    public ProxyLoadException(String message) {
        super(message);
    }

    public ProxyLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxyLoadException(Throwable cause) {
        super(cause);
    }

    public ProxyLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
