package at.fhv.roomix.persist.exeption;

import java.util.List;

public class PersistInternalException extends Exception {

    private Exception innerException;

    public PersistInternalException(Exception innerException) {
        this.innerException = innerException;
    }

    public PersistInternalException(String message, Exception innerException) {
        super(message);
        this.innerException = innerException;
    }

    public Exception getInnerException() {
        return innerException;
    }
}
