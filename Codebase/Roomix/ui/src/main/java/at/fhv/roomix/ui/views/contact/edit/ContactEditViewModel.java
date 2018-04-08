package at.fhv.roomix.ui.views.contact.edit;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.FunctionBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import de.saxsys.mvvmfx.utils.validation.Validator;
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

    @Inject
    private ResourceBundle resourceBundle;

    private final ModelWrapper<ContactPojo> contactWrapper = new ModelWrapper<>();
    private final CompositeValidator formValidator = new CompositeValidator();

    public ContactEditViewModel() {
        firstNameValidator = new FunctionBasedValidator<>(
                firstNameProperty(),
                firstName -> firstName != null && !firstName.trim().isEmpty(),
                ValidationMessage.error("Firstname may not be empty"));
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

    Validator getFirstNameValidator() {
        return firstNameValidator;
    }

    StringProperty firstNameProperty() {
        return contactWrapper.field("firstName", ContactPojo::getFirstName, ContactPojo::setFirstName);
    }




}
