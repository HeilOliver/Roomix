package at.fhv.roomix.ui.view.contact;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.common.ISelectionReadWriteAble;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact
 * ContactViewModel
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
@ScopeProvider(scopes = ContactViewScope.class)
public class ContactViewModel implements ViewModel, ISelectionReadWriteAble<ContactPojo> {
    private BooleanProperty contentViewVisible = new SimpleBooleanProperty();
    private BooleanProperty editViewVisible = new SimpleBooleanProperty();

    @InjectScope
    private ContactViewScope viewScope;

    public void initialize() {
        viewScope.subscribe(ContactViewScope.commandContentView, (key, payload) -> showContent());
        viewScope.subscribe(ContactViewScope.commandEditView, (key, payload) -> showEdit());
        showContent();
    }

    private void showContent() {
        contentViewVisible.setValue(true);
        editViewVisible.setValue(false);
    }

    private void showEdit() {
        contentViewVisible.setValue(false);
        editViewVisible.setValue(true);
    }

    BooleanProperty contentViewVisibleProperty() {
        return contentViewVisible;
    }

    BooleanProperty editViewVisibleProperty() {
        return editViewVisible;
    }

    @Override
    public ReadOnlyObjectProperty<ContactPojo> getSelectionProperty() {
        return viewScope.selectedPojoProperty();
    }

    @Override
    public void setSelection(ContactPojo value) {
        viewScope.selectedPojoProperty().setValue(value);
    }
}
