package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.views.contact.ContactProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactListViewModel
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
public class ContactListViewModel implements ViewModel {

    private final ObservableList<ContactListTableModel> contacts = FXCollections.observableArrayList();
    private final ObjectProperty<ContactListTableModel> selectedTableRow = new SimpleObjectProperty<>();

    public ContactListViewModel() {
        selectedTableRow.addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                ContactProvider.getInstance().selectedContactProperty().setValue(null);
            } else {
                ContactProvider.getInstance().selectedContactProperty().setValue(newValue.getPojo());
            }
        }));
        ContactProvider.getInstance().getContacts().addListener((ListChangeListener<ContactPojo>) c -> {
            loadData();
        });
        loadData();
    }

    private void loadData() {
        contacts.clear();
        ContactProvider.getInstance().getContacts()
                .forEach(contactPojo -> contacts.add(new ContactListTableModel(contactPojo)));
    }


    public ObservableList<ContactListTableModel> getContacts() {
        return contacts;
    }

    public ObjectProperty<ContactListTableModel> selectedTableRowProperty() {
        return selectedTableRow;
    }
}
