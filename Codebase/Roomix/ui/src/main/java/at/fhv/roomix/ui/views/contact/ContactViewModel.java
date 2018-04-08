package at.fhv.roomix.ui.views.contact;

import at.fhv.roomix.ui.views.contact.scope.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
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
public class ContactViewModel implements ViewModel {
    private BooleanProperty listViewEnabled = new SimpleBooleanProperty();
    private BooleanProperty editViewEnabled = new SimpleBooleanProperty();

//    @InjectScope
//    private ContactViewScope contactViewScope;

    public ContactViewModel() {
        listViewEnabled.setValue(true);

//        contactViewScope.subscribe(
//                ContactViewScope.NEW, (s, objects) -> newCall());
//        contactViewScope.subscribe(
//                ContactViewScope.DISCARD, (s, objects) -> discardCall());
    }

    private void newCall() {
        listViewEnabled.setValue(false);
        editViewEnabled.setValue(true);
    }

    private void discardCall() {
        listViewEnabled.setValue(true);
        editViewEnabled.setValue(false);
    }

    BooleanProperty listViewEnabledProperty() {
        return listViewEnabled;
    }

    BooleanProperty editViewEnabledProperty() {
        return editViewEnabled;
    }
}
