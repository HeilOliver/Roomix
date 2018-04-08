package at.fhv.roomix.ui.views.contact;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.views.contact.scope.ContactMasterDetailScope;
import at.fhv.roomix.ui.views.contact.scope.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact
 * ContactViewModel
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
@ScopeProvider(scopes = ContactViewScope.class)
public class ContactViewModel implements ViewModel {
    private BooleanProperty listViewEnabled = new SimpleBooleanProperty();
    private BooleanProperty editViewEnabled = new SimpleBooleanProperty();

    @InjectScope
    private ContactViewScope contactViewScope;

    public ContactViewModel() {
    }

    public void initialize() {
        listViewEnabled.setValue(true);

        contactViewScope.subscribe(
                ContactViewScope.NEW, (s, objects) -> newCall());
        contactViewScope.subscribe(
                ContactViewScope.EDIT, (s, objects) -> editCall());
        contactViewScope.subscribe(
                ContactViewScope.CLOSE, (s, objects) -> closeCall());

    }

    private void editCall() {
        ContactPojo contact = contactViewScope.getSelectedContact();
        if (contact == null) return;
        contactViewScope.inEditProperty().setValue(contact);
        newCall();
    }

    private void newCall() {
        listViewEnabled.setValue(false);
        editViewEnabled.setValue(true);
    }

    private void closeCall() {
        listViewEnabled.setValue(true);
        editViewEnabled.setValue(false);
        contactViewScope.inEditProperty().setValue(null);
    }

    BooleanProperty listViewEnabledProperty() {
        return listViewEnabled;
    }

    BooleanProperty editViewEnabledProperty() {
        return editViewEnabled;
    }
}
