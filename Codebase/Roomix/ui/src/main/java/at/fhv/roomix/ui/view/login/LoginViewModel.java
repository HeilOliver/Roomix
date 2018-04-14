package at.fhv.roomix.ui.view.login;

import at.fhv.roomix.ui.dataprovider.LoginProvider;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.*;

import java.util.Objects;

/**
 * Roomix
 * at.fhv.roomix.ui.view.login
 * LoginViewModel
 * 14/04/2018 Oliver
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
    private BooleanProperty inErrorState = new SimpleBooleanProperty();

    @InjectScope
    private SwitchablePage scope;

    public LoginViewModel() {
        LoginProvider.currentSessionProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        logOutPossible.setValue(false);
                    } else {
                        logOutPossible.setValue(true);
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

        // Validation False when error state == true
        Validator errorStateValidator = new FunctionBasedValidator<>(
                inErrorState,
                errorState -> !errorState,
                ValidationMessage.error(""));

        // Validation False when user is logged in
        Validator loggedInValidator = new FunctionBasedValidator<>(
                LoginProvider.currentSessionProperty(),
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
            resetErrorState();
        });

        password.addListener(observable -> {
            resetErrorState();
        });

        logInPossible.bind(formValidator.getValidationStatus().validProperty());
    }

    private void resetErrorState() {
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
        LoginProvider.logOut();
    }

    void logIn() {
        if (!formValidator.getValidationStatus().isValid()) return;
        LoginProvider.logIn(username.get(), password.get(), (e) -> {
            inErrorState.setValue(true);
        });
    }

    StringProperty usernameProperty() {
        return username;
    }

    ReadOnlyBooleanProperty inProcessProperty() {
        return LoginProvider.inProcess();
    }

    StringProperty passwordProperty() {
        return password;
    }

    BooleanProperty logOutPossibleProperty() {
        return logOutPossible;
    }
}
