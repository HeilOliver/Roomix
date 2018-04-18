package at.fhv.roomix.controller.contact.exeption;

/**
 * Roomix
 * at.fhv.roomix.controller.contact.exeption
 * SessionFaultException
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SessionFaultException extends FaultException {
    public SessionFaultException() {
    }

    public SessionFaultException(String message) {
        super(message);
    }
}
