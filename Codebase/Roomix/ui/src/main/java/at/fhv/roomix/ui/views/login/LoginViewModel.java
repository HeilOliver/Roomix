package at.fhv.roomix.ui.views.login;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;


/**
 * Roomix
 * at.fhv.roomix.ui.views.login
 * LoginViewModel
 * 06/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class LoginViewModel implements ViewModel {

    private final CompositeValidator formValidator = new CompositeValidator();
    private BooleanProperty logInPossible = new SimpleBooleanProperty();
    private BooleanProperty logOutPossible = new SimpleBooleanProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private Validator usernameValidator;
    private Validator passwordValidator;
    private BooleanProperty inErrorState;

    public LoginViewModel() {
        LoginProvider.getInstance().currentSessionProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        logOutPossible.setValue(false);
                    } else {
                        logOutPossible.setValue(true);
                    }
                }));

        inErrorState = LoginProvider.getInstance()
                .inErrorStateProperty();

        usernameValidator = new FunctionBasedValidator<>(
                username,
                username -> username != null && !username.trim().isEmpty(),
                ValidationMessage.error("Username may not be empty"));

        passwordValidator = new FunctionBasedValidator<>(
                password,
                password -> password != null && !password.trim().isEmpty(),
                ValidationMessage.error("Password may not be empty"));

        // Validation False when error state == true
        Validator errorStateValidator = new FunctionBasedValidator<>(
                inErrorState,
                errorState -> !errorState,
                ValidationMessage.error(""));

        // Validation False when user is logged in
        Validator loggedInValidator = new FunctionBasedValidator<>(
                LoginProvider.getInstance().currentSessionProperty(),
                Objects::isNull,
                ValidationMessage.error(""));

        formValidator.addValidators(
                usernameValidator,
                passwordValidator,
                errorStateValidator,
                loggedInValidator
        );
        // Reset Error state when username or password change
        username.addListener(observable -> {
            setProperty();
        });

        password.addListener(observable -> {
            setProperty();
        });

        logInPossible.bind(formValidator.getValidationStatus().validProperty());
    }

    private void setProperty() {
        if (inErrorState.get())
            inErrorState.setValue(false);
    }

    BooleanProperty inErrorStateProperty() {
        return inErrorState;
    }

    BooleanProperty logInPossibleProperty() {
        return logInPossible;
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

    BooleanProperty logOutPossibleProperty() {
        return logOutPossible;
    }
}
