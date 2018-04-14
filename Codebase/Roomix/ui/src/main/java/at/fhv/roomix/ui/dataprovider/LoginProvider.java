package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.session.ISessionController;
import at.fhv.roomix.controller.session.SessionControllerFactory;
import at.fhv.roomix.controller.session.exception.AuthenticationFaultException;
import at.fhv.roomix.controller.session.model.SessionPojo;
import at.fhv.roomix.ui.common.ICallable;
import at.fhv.roomix.ui.common.IErrorCall;
import javafx.application.Platform;
import javafx.beans.property.*;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * LoginProvider
 * 14/04/2018 Oliver
 * <p>
 * The Provider class for login purpose. It holds the current logged in User
 * and his SessionId
 */
public class LoginProvider extends AbstractProvider{
    private static ObjectProperty<SessionPojo> currentSession = new SimpleObjectProperty<>();
    private static BooleanProperty isLoggedIn = new SimpleBooleanProperty();

    static {
        currentSession.addListener(((observable, oldValue, newValue) -> {
            isLoggedIn.setValue(newValue != null);
        }));
    }

    private LoginProvider(){
    }

    public static void logIn(String username, String password, IErrorCall onError) {
        submit(() -> {
            ISessionController instance = SessionControllerFactory.getInstance();
            try {
                SessionPojo session = instance.getSession(username, password);
                Platform.runLater(() -> currentSession.setValue(session));
            } catch (AuthenticationFaultException e) {
                Platform.runLater(() -> onError.errorOccurred(new Error(e)));
            }
        });
    }

    public static void logOut() {
        if (currentSessionProperty().get() == null) return;
        submit(() -> {
            ISessionController instance = SessionControllerFactory.getInstance();
            instance.closeSession(getSessionID());
            Platform.runLater(() -> currentSession.setValue(null));
        });
    }

    public static ObjectProperty<SessionPojo> currentSessionProperty() {
        return currentSession;
    }

    public static ReadOnlyBooleanProperty isLoggedIn() {
        return isLoggedIn;
    }

    static long getSessionID() {
        if (currentSessionProperty().get() == null) return 0;
        SessionPojo session = currentSessionProperty().get();
        return session.getSessionId();
    }
}
