package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.ui.views.contact.scope.ContactMasterDetailScope;
import at.fhv.roomix.ui.views.contact.scope.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

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

    public void newContact(){
        viewScope.publish(ContactViewScope.NEW);
    }

    public void editContact(){

    }

    public void archiveContact(){

    }

    public BooleanProperty contactSelectedProperty() {
        return contactSelected;
    }
}
