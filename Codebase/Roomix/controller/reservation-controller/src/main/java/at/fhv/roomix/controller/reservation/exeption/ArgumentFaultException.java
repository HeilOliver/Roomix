package at.fhv.roomix.controller.reservation.exeption;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.exeption
 * ArgumentFaultException
 * 08.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ArgumentFaultException extends FaultException {

    public ArgumentFaultException() {
        super();
    }

    public ArgumentFaultException(String message) {
        super(message);
    }
}