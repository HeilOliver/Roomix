package at.fhv.roomix.controller.contact.exeption;

import java.util.Set;

/**
 * Roomix
 * at.fhv.roomix.controller.contact.exeption
 * ValidationFault
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ValidationFault extends FaultException {
    private Set<String> validationViolations;

    public ValidationFault(Set<String> validationViolations) {
        this.validationViolations = validationViolations;
    }

    public ValidationFault(String message, Set<String> validationViolations) {
        super(message);
        this.validationViolations = validationViolations;
    }

    public ValidationFault() {
        super();
    }

    public ValidationFault(String message) {
        super(message);
    }

    public Set<String> getValidationViolations() {
        return validationViolations;
    }
}
