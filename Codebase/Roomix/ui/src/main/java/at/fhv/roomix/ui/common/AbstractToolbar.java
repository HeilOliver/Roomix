package at.fhv.roomix.ui.common;

import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * AbstractToolbar
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class AbstractToolbar<T extends AbstractMasterEditScope> {
    private BooleanProperty inEditView = new SimpleBooleanProperty();
    private BooleanProperty inContentView = new SimpleBooleanProperty();
    private BooleanProperty editAble = new SimpleBooleanProperty();
    private BooleanProperty saveAble = new SimpleBooleanProperty();

    private T viewScope;

    public void initialize(T viewScope) {
        this.viewScope = viewScope;
        viewScope.subscribe(AbstractMasterEditScope.commandEditView, (key, payload) -> {
            inEditView.setValue(true);
            inContentView.setValue(false);
        });

        viewScope.subscribe(AbstractMasterEditScope.commandContentView, (key, payload) -> {
            inEditView.setValue(false);
            inContentView.setValue(true);
        });

        viewScope.selectedPojoProperty().addListener(
                (observable, oldValue, newValue) -> editAble.setValue(newValue != null));

        saveAble.bind(viewScope.inEditPropertyValidProperty());

        inContentView.setValue(true);
    }

    public void onSave() {
        viewScope.publish(ContactViewScope.commandSave);
    }

    public void onCancel() {
        viewScope.publish(ContactViewScope.commandCancel);
    }

    public void onEdit() {
        viewScope.publish(ContactViewScope.commandEdit);
    }

    public void onNew() {
        viewScope.publish(ContactViewScope.commandNew);
    }

    public BooleanProperty inEditViewProperty() {
        return inEditView;
    }

    public BooleanProperty saveAbleProperty() {
        return saveAble;
    }

    public BooleanProperty editAbleProperty() {
        return editAble;
    }

    public BooleanProperty inContentViewProperty() {
        return inContentView;
    }

    public StringProperty getSearchQueryProperty() {
        return viewScope.getSearchQueryProperty();
    }

    public ReadOnlyBooleanProperty getInProcessProperty() {
        return viewScope.inProcess();
    }
}
