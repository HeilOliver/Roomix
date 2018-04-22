package at.fhv.roomix.ui.common.validator;

import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRules;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Roomix
 * at.fhv.roomix.ui.common.validator
 * DateValidator
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class DateValidator extends ObservableRuleBasedValidator {

    public DateValidator(ObservableValue<LocalDate> source) {
        addRule(ObservableRules.fromPredicate(source, Objects::nonNull), ValidationMessage.error("Date may not be empty"));
    }

}
