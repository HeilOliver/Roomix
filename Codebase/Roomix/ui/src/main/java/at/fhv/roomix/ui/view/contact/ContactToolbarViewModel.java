package at.fhv.roomix.ui.view.contact;

import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactToolbarViewModel
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactToolbarViewModel implements ViewModel {
    private BooleanProperty inEditView = new SimpleBooleanProperty();
    private BooleanProperty inContentView = new SimpleBooleanProperty();
    private BooleanProperty editAble = new SimpleBooleanProperty();
    private BooleanProperty saveAble = new SimpleBooleanProperty();

    @InjectScope
    private ContactViewScope viewScope;

    public void initialize() {
        viewScope.subscribe(ContactViewScope.commandEditView, (key, payload) -> {
            inEditView.setValue(true);
            inContentView.setValue(false);
        });

        viewScope.subscribe(ContactViewScope.commandContentView, (key, payload) -> {
            inEditView.setValue(false);
            inContentView.setValue(true);
        });

        viewScope.selectedPojoProperty().addListener(
                (observable, oldValue, newValue) -> editAble.setValue(newValue != null));

        saveAble.bind(viewScope.inEditPropertyValidProperty());

        inContentView.setValue(true);
    }

    void onSave() {
        viewScope.publish(ContactViewScope.commandSave);
    }

    void onCancel() {
        viewScope.publish(ContactViewScope.commandCancel);
    }

    void onEdit() {
        viewScope.publish(ContactViewScope.commandEdit);
    }

    void onNew() {
        viewScope.publish(ContactViewScope.commandNew);
    }

    BooleanProperty inEditViewProperty() {
        return inEditView;
    }

    public BooleanProperty saveAbleProperty() {
        return saveAble;
    }

    public BooleanProperty editAbleProperty() {
        return editAble;
    }

    BooleanProperty inContentViewProperty() {
        return inContentView;
    }

    StringProperty getSearchQueryProperty() {
        return viewScope.getSearchQueryProperty();
    }

    ReadOnlyBooleanProperty getInProcessProperty() {
       return viewScope.inProcess();
    }
}
