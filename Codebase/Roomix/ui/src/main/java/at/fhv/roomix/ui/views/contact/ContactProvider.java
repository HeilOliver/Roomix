package at.fhv.roomix.ui.views.contact;

import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.exeption.FaultException;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

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

    private static final Object lock = new Object();
    private static ContactProvider instance;

    public ContactProvider() {
        refreshData();
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

    private void refreshData() {
        IReservationController instance = ReservationControllerFactory.getInstance();
        Collection<ContactPojo> allContacts = null;
        try {
            allContacts = instance.getAllContacts(122);
        } catch (FaultException e) {
            e.printStackTrace();
        }
        contacts.addAll(allContacts);
    }

    public void newContact() {

    }

    public void editContact() {

    }

    private BooleanProperty inProcess = new SimpleBooleanProperty();

    public BooleanProperty inProcessProperty() {
        return inProcess;
    }

    public ObservableList<ContactPojo> getContacts() {
        return contacts;
    }
}
