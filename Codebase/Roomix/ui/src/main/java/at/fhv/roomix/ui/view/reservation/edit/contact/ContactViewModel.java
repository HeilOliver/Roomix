package at.fhv.roomix.ui.view.reservation.edit.contact;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.contact
 * ContactViewModel
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
@ScopeProvider(scopes = ContactScope.class)
public class ContactViewModel extends SubscribeAbleViewModel<ContactPojo> {
    private BooleanProperty inEditView = new SimpleBooleanProperty();
    private BooleanProperty isValidPojo = new SimpleBooleanProperty();
    private BooleanProperty isSelected = new SimpleBooleanProperty();

    @InjectScope
    private ContactScope scope;

    public ContactViewModel() {
    }

    public void initialize() {
        isValid = isValidPojo;
        isSelected.bind(scope.selectedPojoProperty().isNotNull());
    }

    void onCancel() {
        scope.publish(ContactScope.commandCancel);
        inEditView.setValue(false);
    }

    void onCommit() {
        if (scope.selectedPojoProperty().get() == null) return;
        if (inEditView.get()) {
            scope.publish(ContactScope.commandCommit);
            isValidPojo.setValue(scope.isEditValidProperty().get());
        } else {
            isValidPojo.setValue(true);
        }
        currModel.setValue(scope.selectedPojoProperty().get());
        commit();
    }

    void onEdit() {
        if (scope.selectedPojoProperty().get() == null) return;
        scope.publish(ContactScope.commandEdit);
        inEditView.setValue(true);
    }

    void onNew() {
        scope.selectedPojoProperty().setValue(emptyPojoSupplier.get());
        scope.publish(ContactScope.commandEdit);
        inEditView.setValue(true);
    }

    StringProperty searchQueryProperty() {
        return scope.getSearchQueryProperty();
    }

    ReadOnlyBooleanProperty inEditViewProperty() {
        return inEditView;
    }

    ReadOnlyBooleanProperty inProgressProperty() {
        return scope.inProgressProperty();
    }

    ReadOnlyBooleanProperty isSelectedProperty() {
        return isSelected;
    }

    @Override
    protected void afterUnSubscribe() {
        scope.selectedPojoProperty().setValue(null);
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        if (!isNew) {
            scope.selectedPojoProperty().setValue(currModel.get());
        }
    }
}
