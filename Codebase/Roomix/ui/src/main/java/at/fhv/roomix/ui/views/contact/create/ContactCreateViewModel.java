package at.fhv.roomix.ui.views.contact.create;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.create
 * ContactCreateViewModel
 * 06.04.2018 sge
 * <p>
 * Enter Description here
 */
@Singleton
public class ContactCreateViewModel implements ViewModel {
    private BooleanProperty isValid = new SimpleBooleanProperty();

    public BooleanProperty isValidProperty() {
        return isValid;
    }

}
