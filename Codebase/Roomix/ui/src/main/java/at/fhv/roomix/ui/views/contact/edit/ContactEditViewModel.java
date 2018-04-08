package at.fhv.roomix.ui.views.contact.edit;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.views.contact.ContactProvider;
import at.fhv.roomix.ui.views.contact.scope.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ResourceBundle;

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
    private final Validator firstNameValidator;
    private final BooleanProperty inProgress;
    private final ModelWrapper<ContactPojo> contactWrapper = new ModelWrapper<>();
    private final CompositeValidator formValidator = new CompositeValidator();

    @Inject
    private ResourceBundle resourceBundle;
    @InjectScope
    private ContactViewScope scope;

    public ContactEditViewModel() {
        inProgress = ContactProvider.getInstance().inProcessProperty();
        Validator inProcessValidator = new FunctionBasedValidator<>(
                inProgress,
                inProcess -> !inProcess,
                ValidationMessage.error(""));

        firstNameValidator = new FunctionBasedValidator<>(
                firstNameProperty(),
                firstName -> firstName != null && !firstName.trim().isEmpty(),
                ValidationMessage.error("Firstname may not be empty"));

        formValidator.addValidators(
                firstNameValidator,
                inProcessValidator
        );
    }

    public void initialize() {
        scope.subscribe(ContactViewScope.EDIT_SELECTED, (s, objects) -> {

        });
    }

    private void resetForm() {
        contactWrapper.reset();
    }

    private void initWithContact(ContactPojo contact) {
        this.contactWrapper.set(contact);
        this.contactWrapper.reload();
    }

    private void commitChanges() {
        if (contactWrapper.get() == null) {
            contactWrapper.set(new ContactPojo());
        }
        contactWrapper.commit();
    }

    ValidationStatus getFirstNameValidator() {
        return firstNameValidator.getValidationStatus();
    }

    StringProperty firstNameProperty() {
        return contactWrapper.field("firstName", ContactPojo::getFirstName, ContactPojo::setFirstName);
    }

    void discard() {
        resetForm();
        scope.publish(ContactViewScope.DISCARD);
    }

    void save(ICallable errorCallback) {
        if (!formValidator.getValidationStatus().isValid()) {
            return;
        }


        if (errorCallback == null) return;
        errorCallback.call();
    }

    BooleanProperty inProgressProperty() {
        return inProgress;
    }

    ReadOnlyBooleanProperty savePossibleProperty() {
        return formValidator.getValidationStatus().validProperty();
    }

    interface ICallable {
        void call();
    }

}
