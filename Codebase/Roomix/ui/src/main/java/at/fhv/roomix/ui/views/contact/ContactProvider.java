package at.fhv.roomix.ui.views.contact;

import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.exeption.FaultException;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.common.Search;
import at.fhv.roomix.ui.config.SessionProvider;
import at.fhv.roomix.ui.views.contact.edit.ContactEditViewModel.ICallable;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableSet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact
 * ContactProvider
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactProvider {
    private static final Object lock = new Object();
    private static ContactProvider instance;

    private final ObservableSet<ContactPojo> contacts;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private BooleanProperty inProcess = new SimpleBooleanProperty();
    private Search<ContactPojo> search;
    private ICallable onError;

    private ContactProvider() {
        search = new Search<>(search ->
                ReservationControllerFactory.getInstance()
                        .getSearchedContacts(SessionProvider.getSessionId(), search)
                , this::onError);
        inProcess.or(search.getInProcessProperty());
        contacts = search.getQueryResultSet();
    }

    public static ContactProvider getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ContactProvider();
            }
        }
        return instance;
    }

    private void onError() {
        if (onError == null) return;
        onError.call();
    }

    public void setErrorCallback(ICallable onError) {
        this.onError = onError;
    }

    public StringProperty searchQuery() {
        return search.currentQueryProperty();
    }

    public ReadOnlyBooleanProperty inProcessProperty() {
        return inProcess;
    }

    public ObservableSet<ContactPojo> getContacts() {
        return contacts;
    }

    public void saveOrUpdate(ContactPojo tempContactPojo,
                             ICallable successCallback,
                             ICallable errorCallback) {
        IReservationController instance = ReservationControllerFactory.getInstance();
        executor.submit(() -> {
            Platform.runLater(() -> inProcess.setValue(true));
            try {
                instance.updateContact(SessionProvider.getSessionId(), tempContactPojo);
                if (successCallback != null)
                    Platform.runLater(successCallback::call);
            } catch (FaultException e) {
                if (successCallback != null)
                    Platform.runLater(errorCallback::call);
            } finally {
                Platform.runLater(() -> inProcess.setValue(false));
            }
        });
    }
}
