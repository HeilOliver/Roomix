package at.fhv.roomix.ui.common;

import at.fhv.roomix.ui.dataprovider.AbstractSearchEditProvider;
import at.fhv.roomix.ui.dataprovider.ContactProvider;
import at.fhv.roomix.ui.dataprovider.SearchProvider;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;
import javafx.collections.ObservableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * AbstractMDScope
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class AbstractMDScope<T> implements Scope {
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractMDScope.class);

    public static final String commandSave = "Command_SAVE";
    public static final String commandCancel = "Command_CLOSE";
    public static final String commandEdit = "Command_EDIT";
    public static final String commandNew = "Command_NEW";
    public static final String commandEditView = "Command_EditView";
    public static final String commandContentView = "Command_ContentView";
    public static final String commandCommitEdit = "Command_CommitEdit";

    protected final AbstractSearchEditProvider<T> provider;
    protected final ObjectProperty<T> selectedPojo = new SimpleObjectProperty<>();
    protected final ObjectProperty<T> inEditPojo = new SimpleObjectProperty<>();
    protected final BooleanProperty inEditPropertyValid = new SimpleBooleanProperty();
    private final Supplier<T> supplier;

    protected IErrorCall onSaveUpdateError;
    protected IErrorCall onSearchError;

    public AbstractMDScope(Supplier<T> valueSupplier, AbstractSearchEditProvider<T> provider) {
        this.provider = provider;
        supplier = valueSupplier;

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
        inEditPojo.setValue(supplier.get());
        publish(commandEditView);
    }

    public ReadOnlyBooleanProperty inProcess() {
        return provider.getInProcessProperty();
    }

    public ObjectProperty<T> selectedPojoProperty() {
        return selectedPojo;
    }

    public ObjectProperty<T> inEditPojoProperty() {
        return inEditPojo;
    }


    public ObservableSet<T> getObservableSet() {
        return provider.getQueryResultSet();
    }

    public StringProperty getSearchQueryProperty() {
        return provider.currentQueryProperty();
    }
}
