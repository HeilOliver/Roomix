package at.fhv.roomix.persist.exeption;

public class PersistInternalException extends Exception {

    private Throwable innerException;

    public PersistInternalException(Throwable innerException) {
        this.innerException = innerException;
    }

    public PersistInternalException(String message, Exception innerException) {
        super(message);
        this.innerException = innerException;
    }

    public Throwable getInnerException() {
        return innerException;
    }
}
