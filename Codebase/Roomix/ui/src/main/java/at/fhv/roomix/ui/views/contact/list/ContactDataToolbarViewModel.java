package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.ui.views.contact.ContactProvider;
import at.fhv.roomix.ui.views.contact.edit.ContactEditViewModel.ICallable;
import at.fhv.roomix.ui.views.contact.scope.ContactMasterDetailScope;
import at.fhv.roomix.ui.views.contact.scope.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;

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

    @InjectScope
    private ContactMasterDetailScope mdScope;

    @InjectScope
    private ContactViewScope viewScope;

    public void initialize() {
        mdScope.selectedContactProperty().addListener((observable, oldValue, newValue) -> {
            contactSelected.setValue(newValue != null);
        });
    }

    public StringProperty searchQueryProperty() {
        return ContactProvider.getInstance().searchQuery();
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

    public ReadOnlyBooleanProperty inProcessProperty() {
        return ContactProvider.getInstance().inProcessProperty();
    }

    public BooleanProperty contactSelectedProperty() {
        return contactSelected;
    }

    public void setErrorCall(ICallable onError) {
        ContactProvider.getInstance().setErrorCallback(onError);
    }
}
