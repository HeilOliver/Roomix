package at.fhv.roomix.ui.views.contact;

import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.exeption.FaultException;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.config.SessionProvider;
import at.fhv.roomix.ui.views.contact.edit.ContactEditViewModel;
import at.fhv.roomix.ui.views.contact.edit.ContactEditViewModel.ICallable;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact
 * ContactProvider
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactProvider {
    private final ObservableList<ContactPojo> contacts = FXCollections.observableArrayList();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final Object lock = new Object();
    private static ContactProvider instance;

    private ContactProvider() {
        get(() -> {}, ()-> {}, "");
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

    public void get(ICallable successCallback,
                    ICallable errorCallback, String query) {

        IReservationController instance = ReservationControllerFactory.getInstance();
        executor.submit(() -> {
            Platform.runLater(()-> inProcess.setValue(true));
            try {
                Collection<ContactPojo> loadedContacts
                        = instance.getAllContacts(SessionProvider.getSessionId());
                List<ContactPojo> filteredPojo = loadedContacts.stream()
                        .filter(c -> c.getFname().contains(query) ||
                                c.getLname().contains(query) ||
                                c.getStreet().contains(query) ||
                                c.getPostcode().contains(query) ||
                                c.getPlace().contains(query))
                        .collect(Collectors.toList());
                Platform.runLater(() -> {
                    contacts.clear();
                    contacts.addAll(filteredPojo);
                });
                if (successCallback != null)
                    Platform.runLater(successCallback::call);
            } catch (FaultException e) {
                if (successCallback != null)
                    Platform.runLater(errorCallback::call);
            } finally {
                Platform.runLater(()-> inProcess.setValue(false));
            }
        });
    }

    private BooleanProperty inProcess = new SimpleBooleanProperty();

    public BooleanProperty inProcessProperty() {
        return inProcess;
    }

    public ObservableList<ContactPojo> getContacts() {
        return contacts;
    }

    public void saveOrUpdate(ContactPojo tempContactPojo,
                             ICallable successCallback,
                             ICallable errorCallback) {
        IReservationController instance = ReservationControllerFactory.getInstance();
        executor.submit(() -> {
            Platform.runLater(()-> inProcess.setValue(true));
            try {
                instance.updateContact(SessionProvider.getSessionId(), tempContactPojo);
                if (successCallback != null)
                    Platform.runLater(successCallback::call);
            } catch (FaultException e) {
                if (successCallback != null)
                    Platform.runLater(errorCallback::call);
            } finally {
                Platform.runLater(()-> inProcess.setValue(false));
            }
        });
    }
}
