package at.fhv.roomix.ui.view.contact.content;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.dataprovider.ContactProvider;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactTableViewModel
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactTableViewModel implements ViewModel {

    private final ObservableList<ContactListTableModel> contacts =
            FXCollections.observableArrayList();

    private final ObjectProperty<ContactListTableModel> selectedTableRow =
            new SimpleObjectProperty<>();

    @InjectScope
    private ContactViewScope viewScope;

    public void initialize() {
        selectedTableRow.addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                viewScope.selectedPojoProperty().setValue(null);
            } else {
                viewScope.selectedPojoProperty().setValue(newValue.getPojo());
            }
        }));

        viewScope.getObservableSet().addListener(
                (SetChangeListener<ContactPojo>) c -> loadData());
    }

    private void loadData() {
        contacts.clear();
        viewScope.getObservableSet()
                .forEach(contactPojo
                        -> contacts.add(new ContactListTableModel(
                        contactPojo)));
    }

    ObservableList<ContactListTableModel> getContacts() {
        return contacts;
    }

    ObjectProperty<ContactListTableModel> selectedTableRowProperty() {
        return selectedTableRow;
    }
}
