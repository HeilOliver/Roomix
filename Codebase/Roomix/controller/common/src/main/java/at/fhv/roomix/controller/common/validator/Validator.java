package at.fhv.roomix.controller.common.validator;

import at.fhv.roomix.controller.common.exceptions.ValidationFault;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * Roomix
 * at.fhv.roomix.controller.common.validator
 * Validator
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Validator {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private Validator() {
    }

    public static  <T> void validate(T object) throws ValidationFault {
        javax.validation.Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (violations.isEmpty()) return;

        Set<String> strings = new HashSet<>();
        violations.forEach((v) -> strings.add(v.getMessage()));
        throw new ValidationFault(strings);
    }

}
