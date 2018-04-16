package at.fhv.roomix.ui.view.contact.scopes;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.dataprovider.ContactProvider;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;
import javafx.collections.ObservableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.scopes
 * ContactViewScope
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactViewScope implements Scope {
    public static final String commandSave = "ContactCommand_SAVE";
    public static final String commandCancel = "ContactCommand_CLOSE";
    public static final String commandEdit = "ContactCommand_EDIT";
    public static final String commandNew = "ContactCommand_NEW";
    public static final String commandEditView = "ContactCommand_EditView";
    public static final String commandContentView = "ContactCommand_ContentView";
    public static final String commandCommitEdit = "ContactCommand_CommitEdit";
    private static final Logger LOG = LoggerFactory.getLogger(ContactViewScope.class);
    private final ContactProvider provider;
    private ObjectProperty<ContactPojo> selectedPojo = new SimpleObjectProperty<>();
    private ObjectProperty<ContactPojo> inEditPojo = new SimpleObjectProperty<>();
    private BooleanProperty inEditPropertyValid = new SimpleBooleanProperty();
    private IErrorCall onSaveUpdateError;
    private IErrorCall onSearchError;

    public ContactViewScope() {
        provider = new ContactProvider(onSearchError);
        subscribe(ContactViewScope.commandCancel, (key, payload) -> closeCommand());
        subscribe(ContactViewScope.commandSave, (key, payload) -> saveCommand());
        subscribe(ContactViewScope.commandEdit, (key, payload) -> editCommand());
        subscribe(ContactViewScope.commandNew, (key, payload) -> newCommand());
    }

    private void closeCommand() {
        inEditPojo.setValue(null);
        publish(commandContentView);
    }

    private void saveCommand() {
        publish(commandCommitEdit);
        provider.saveOrUpdate(inEditPojo.get(), onSaveUpdateError, this::closeCommand);
    }

    private void editCommand() {
        if (selectedPojo.get() == null) return;
        inEditPojo.setValue(selectedPojo.get());
        publish(commandEditView);
    }

    public BooleanProperty inEditPropertyValidProperty() {
        return inEditPropertyValid;
    }

    private void newCommand() {
        inEditPojo.setValue(new ContactPojo());
        publish(commandEditView);
    }

    public ReadOnlyBooleanProperty inProcess() {
        return provider.getInProcessProperty();
    }

    public ObjectProperty<ContactPojo> selectedPojoProperty() {
        return selectedPojo;
    }

    public ObjectProperty<ContactPojo> inEditPojoProperty() {
        return inEditPojo;
    }

    public ObservableSet<ContactPojo> getObservableSet() {
        return provider.getQueryResultSet();
    }

    public StringProperty getSearchQueryProperty() {
        return provider.currentQueryProperty();
    }
}
