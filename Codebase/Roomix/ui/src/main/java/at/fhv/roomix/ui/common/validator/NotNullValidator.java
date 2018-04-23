package at.fhv.roomix.ui.common.validator;

import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRules;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Roomix
 * at.fhv.roomix.ui.common.validator
 * NotNullValidator
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class NotNullValidator extends ObservableRuleBasedValidator {

    public NotNullValidator(ObservableValue<Objects> source) {
        addRule(ObservableRules.fromPredicate(source, Objects::nonNull), ValidationMessage.error("Field may not be empty"));
    }

}
