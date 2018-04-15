package at.fhv.roomix.ui.view.contact.content;

import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactContentViewModel
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactContentViewModel implements ViewModel {
    @InjectScope
    private ContactViewScope viewScope;

    private BooleanProperty detailOpenProperty = new SimpleBooleanProperty();

    public void initialize() {
        viewScope.selectedPojoProperty().addListener(((observable, oldValue, newValue) -> {
            detailOpenProperty.setValue(newValue != null);
        }));

    }

    ReadOnlyBooleanProperty detailOpenProperty() {
        return detailOpenProperty;
    }

}
