package at.fhv.roomix.ui.common.validator;

import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;

import java.util.regex.Pattern;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.validators
 * phonenumberValidator
 * 07.04.2018 sge
 * <p>
 * Enter Description here
 */
public class PhoneValidator extends ObservableRuleBasedValidator {

    private static final Pattern SIMPLE_PHONE_PATTERN = Pattern.compile("\\+?[0-9\\s]{3,20}");

    public PhoneValidator(ObservableValue<String> number, String message) {

        final BooleanBinding phonePatternMatches = Bindings.createBooleanBinding(() -> {
            final String input = number.getValue();

            if (input == null || input.trim().isEmpty()) {
                return false;
            }

            return SIMPLE_PHONE_PATTERN.matcher(input).matches();
        }, number);

        addRule(phonePatternMatches, ValidationMessage.error(message));
    }
}