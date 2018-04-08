package at.fhv.roomix.ui.views.login;

import at.fhv.roomix.controller.session.ISessionController;
import at.fhv.roomix.controller.session.SessionControllerFactory;
import at.fhv.roomix.controller.session.exception.AuthenticationFaultException;
import at.fhv.roomix.controller.session.model.SessionPojo;
import at.fhv.roomix.ui.config.ResourceHandler;
import at.fhv.roomix.ui.config.SessionProvider;
import at.fhv.roomix.ui.views.main.models.SwitchablePage;
import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Roomix
 * at.fhv.roomix.ui.views.login
 * LoginProvider
 * 06/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class LoginProvider {
    private static final Object lock = new Object();
    private static LoginProvider instance;
    private final String emptyUserTag = ResourceHandler.getLocalizedString("main.login");
    private ObjectProperty<SessionPojo> currentSession = new SimpleObjectProperty<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private BooleanProperty inProcess = new SimpleBooleanProperty();
    private StringProperty currentUser = new SimpleStringProperty();

    public static LoginProvider getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new LoginProvider();
            }
        }
        return instance;
    }

    public StringProperty currentUserProperty() {
        return currentUser;
    }

    void logOut() {
        inProcess.setValue(true);
        executor.submit(() -> {
            ISessionController instance = SessionControllerFactory.getInstance();
            SessionPojo sessionPojo = currentSession.get();

            if (sessionPojo == null) {
                Platform.runLater(() -> {
                    inProcess.setValue(false);
                });
                return;
            }

            instance.closeSession(sessionPojo.getSessionId());
            Platform.runLater(() -> {
                currentSession.setValue(null);
                currentUser.setValue(emptyUserTag);
                inProcess.setValue(false);
            });
        });
    }

    ObjectProperty<SessionPojo> currentSessionProperty() {
        return currentSession;
    }

    void logIn(String username, String password) {
        inProcess.setValue(true);
        executor.submit(() -> {
            ISessionController instance = SessionControllerFactory.getInstance();
            try {
                SessionPojo session = instance.getSession(username, password);
                Platform.runLater(() -> {
                    currentSession.setValue(session);
                    currentUser.setValue(session.getName());
                    SessionProvider.setSessionId(session.getSessionId());
                });
            } catch (AuthenticationFaultException e) {
                Platform.runLater(() -> isErrorOccurred.setValue(true));
            } finally {
                Platform.runLater(() -> inProcess.setValue(false));
            }
        });
    }

    private BooleanProperty isErrorOccurred = new SimpleBooleanProperty();

    public BooleanProperty inErrorStateProperty() {
        return isErrorOccurred;
    }

    BooleanProperty inProcessProperty() {
        return inProcess;
    }
}
