package at.fhv.roomix.ui.views.contact.validators;

import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRules;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import javafx.beans.value.ObservableValue;

import java.util.regex.Pattern;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.validators
 * emailValidator
 * 07.04.2018 sge
 * <p>
 * E-Mail Validierung
 */
public class emailValidator extends ObservableRuleBasedValidator {

    private static final Pattern SIMPLE_EMAIL_PATTERN = Pattern
            .compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,8}");

    public emailValidator(ObservableValue<String> source) {
        addRule(ObservableRules.notEmpty(source), ValidationMessage.error("Email may not be empty"));
        addRule(ObservableRules.matches(source, SIMPLE_EMAIL_PATTERN),
                ValidationMessage.warning("Maybe a wrong email format"));
    }

}
