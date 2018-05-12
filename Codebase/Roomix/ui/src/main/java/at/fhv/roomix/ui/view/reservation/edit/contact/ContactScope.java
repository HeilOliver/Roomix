package at.fhv.roomix.ui.view.reservation.edit.contact;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.ui.dataprovider.ReadOnlyContactProvider;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;
import javafx.collections.ObservableSet;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.contact
 * Scope
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactScope implements Scope {
    public static final String commandEdit = "Command_EDIT";
    public static final String commandCancel = "Command_Cancel";
    public static final String commandCommit = "Command_Commit";

    private ObjectProperty<ContactPojo> selectedPojo = new SimpleObjectProperty<>(); // As Model
    private BooleanProperty isEditValid = new SimpleBooleanProperty();  // Validation from Model
    private ReadOnlyContactProvider provider = new ReadOnlyContactProvider();

    public StringProperty getSearchQueryProperty() {
        return provider.currentQueryProperty();
    }

    public BooleanProperty isEditValidProperty() {
        return isEditValid;
    }

    public ObjectProperty<ContactPojo> selectedPojoProperty() {
        return selectedPojo;
    }

    public ObservableSet<ContactPojo> getObservableSet() {
        return provider.getQueryResultSet();
    }

    public ReadOnlyBooleanProperty inProgressProperty() {
        return provider.getInProcessProperty();
    }
}
