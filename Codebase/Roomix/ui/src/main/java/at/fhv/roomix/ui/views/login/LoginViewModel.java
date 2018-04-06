package at.fhv.roomix.ui.views.login;

import at.fhv.roomix.ui.views.main.models.SwitchablePage;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Singleton;

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
    }

    void logOut() {
        password.setValue("");
        LoginProvider.getInstance().logOut();
    }

    void logIn() {
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
