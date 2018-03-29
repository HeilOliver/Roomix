package at.fhv.roomix.ui.views.contact.edit;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.edit
 * ContactEditViewModel
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
public class ContactEditViewModel implements ViewModel {
    private BooleanProperty isValid = new SimpleBooleanProperty();

    public BooleanProperty isValidProperty() {
        return isValid;
    }
}
