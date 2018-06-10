package at.fhv.roomix.controller.reservation;

/**
 * Roomix
 * at.fhv.roomix.implement.reservation
 * ${FILE_NAME}
 * 09/06/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class PDFCreateException extends Exception {
    public PDFCreateException() {
        super();
    }

    public PDFCreateException(String message) {
        super(message);
    }

    public PDFCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PDFCreateException(Throwable cause) {
        super(cause);
    }

    protected PDFCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
