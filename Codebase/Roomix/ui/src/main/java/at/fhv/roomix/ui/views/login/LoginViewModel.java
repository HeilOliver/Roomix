package at.fhv.roomix.ui.views.login;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Roomix
 * at.fhv.roomix.ui.views.login
 * LoginViewModel
 * 06/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class LoginViewModel implements ViewModel {

    private BooleanProperty loggedIn = new SimpleBooleanProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private Validator usernameValidator;
    private Validator passwordValidator;

    private final CompositeValidator formValidator = new CompositeValidator();

    BooleanProperty loggedInProperty() {
        return loggedIn;
    }

    public LoginViewModel() {
        LoginProvider.getInstance().currentSessionProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        loggedIn.setValue(false);
                    } else {
                        loggedIn.setValue(true);
                    }
                }));

        usernameValidator = new FunctionBasedValidator<>(
                username,
                username -> username != null && !username.trim().isEmpty(),
                ValidationMessage.error("Username may not be empty"));

        passwordValidator = new FunctionBasedValidator<>(
                password,
                password -> password != null && !password.trim().isEmpty(),
                ValidationMessage.error("Password may not be empty"));

        formValidator.addValidators(
                usernameValidator,
                passwordValidator
        );
    }

    ValidationStatus usernameValidation() {
        return usernameValidator.getValidationStatus();
    }

    ValidationStatus passwordValidation() {
        return passwordValidator.getValidationStatus();
    }

    void logOut() {
        password.setValue("");
        LoginProvider.getInstance().logOut();
    }

    void logIn() {
        if (!formValidator.getValidationStatus().isValid()) return;
        LoginProvider.getInstance().logIn(username.get(), password.get());
    }

    StringProperty usernameProperty() {
        return username;
    }

    BooleanProperty inProcessProperty() {
        return LoginProvider.getInstance().inProcessProperty();
    }

    StringProperty passwordProperty() {
        return password;
    }
}
