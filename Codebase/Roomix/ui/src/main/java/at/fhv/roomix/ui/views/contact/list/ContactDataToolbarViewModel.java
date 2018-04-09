package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.ui.views.contact.ContactProvider;
import at.fhv.roomix.ui.views.contact.edit.ContactEditViewModel.ICallable;
import at.fhv.roomix.ui.views.contact.scope.ContactMasterDetailScope;
import at.fhv.roomix.ui.views.contact.scope.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactDataToolbar
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactDataToolbarViewModel implements ViewModel {
    private final BooleanProperty contactSelected = new SimpleBooleanProperty();
    private final StringProperty searchQuery = new SimpleStringProperty();
    private BooleanProperty inProcess;

    @InjectScope
    private ContactMasterDetailScope mdScope;

    @InjectScope
    private ContactViewScope viewScope;

    public ContactDataToolbarViewModel() {
        inProcess = ContactProvider.getInstance().inProcessProperty();
    }

    public void initialize() {
        mdScope.selectedContactProperty().addListener((observable, oldValue, newValue) -> {
            contactSelected.setValue(newValue != null);
        });
    }

    public StringProperty searchQueryProperty() {
        return searchQuery;
    }

    public void newContact() {
        viewScope.publish(ContactViewScope.NEW);
    }

    public void editContact() {
        viewScope.publish(ContactViewScope.EDIT);
    }

    public void archiveContact() {
        viewScope.publish(ContactViewScope.ARCHIVE);
    }

    public BooleanProperty inProcessProperty() {
        return inProcess;
    }

    public BooleanProperty contactSelectedProperty() {
        return contactSelected;
    }

    public void search(ICallable errorCallback) {
        String query = searchQuery.get();
        if (query == null) query = "";
        ContactProvider.getInstance().get(null, errorCallback, query);
    }
}
