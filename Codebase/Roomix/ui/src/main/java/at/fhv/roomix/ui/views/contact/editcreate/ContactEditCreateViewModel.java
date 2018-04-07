package at.fhv.roomix.ui.views.contact.editcreate;

import at.fhv.roomix.ui.views.contact.ContactProvider;
import at.fhv.roomix.ui.views.contact.validators.companynameValidator;
import at.fhv.roomix.ui.views.contact.validators.countryValidator;
import at.fhv.roomix.ui.views.contact.validators.emailValidator;
import at.fhv.roomix.ui.views.contact.validators.firstnameValidator;
import at.fhv.roomix.ui.views.contact.validators.lastnameValidator;
import at.fhv.roomix.ui.views.contact.validators.phonenumberValidator;
import at.fhv.roomix.ui.views.contact.validators.placeValidator;
import at.fhv.roomix.ui.views.contact.validators.postcodeValidator;
import at.fhv.roomix.ui.views.contact.validators.streetValidator;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationStatus;
import de.saxsys.mvvmfx.utils.validation.Validator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.editcreate
 * ContactEditCreateViewModel
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
public class ContactEditCreateViewModel implements ViewModel {

    private final ModelWrapper<ContactProvider> contactWrapper = new ModelWrapper<>();

    private final Validator firstnameValidator = new firstnameValidator(firstnameProperty());
    private final Validator lastnameValidator = new lastnameValidator(lastnameProperty());
    private final Validator companynameValidator = new companynameValidator(companynameProperty());
    private final Validator phonenumberValidator = new phonenumberValidator(phonenumberProperty());
    private final Validator streetValidator = new streetValidator(streetProperty());
    private final Validator placeValidator = new placeValidator(placeProperty());
    private final Validator postcodeValidator = new postcodeValidator(postcodeProperty());
    private final Validator countryValidator = new countryValidator(countryProperty());
    private final Validator emailValidator = new emailValidator(emailProperty());

    private BooleanProperty isValid = new SimpleBooleanProperty();

    private final CompositeValidator formValidator = new CompositeValidator();

    public BooleanProperty isValidProperty() {

        formValidator.addValidators(
                firstnameValidator,
                lastnameValidator,
                companynameValidator,
                phonenumberValidator,
                streetValidator,
                placeValidator,
                postcodeValidator,
                countryValidator,
                emailValidator
                );

        return isValid;
    }

    private void resetForm() {
        contactWrapper.reset();
    }

    private void commitChanges() {
        if (contactWrapper.get() == null) {
            contactWrapper.set(new ContactProvider());
        }
    }

    public ValidationStatus firstnameValidation() {
        return firstnameValidator.getValidationStatus();
    }

    public ValidationStatus lastnameValidation() {
        return lastnameValidator.getValidationStatus();
    }

    public ValidationStatus companynameValidation() {
        return companynameValidator.getValidationStatus();
    }

    public ValidationStatus phonenumberValidation() {
        return phonenumberValidator.getValidationStatus();
    }

    public ValidationStatus streetValidation() {
        return streetValidator.getValidationStatus();
    }

    public ValidationStatus placeValidation() {
        return placeValidator.getValidationStatus();
    }

    public ValidationStatus postcodeValidation() {
        return postcodeValidator.getValidationStatus();
    }

    public ValidationStatus countryValidation() {
        return countryValidator.getValidationStatus();
    }

    public ValidationStatus emailValidation() {
        return emailValidator.getValidationStatus();
    }

    public StringProperty firstnameProperty() {
        return contactWrapper.field("firstname", ContactProvider::getFirstname, ContactProvider::setFirstname);
    }

    public StringProperty lastnameProperty() {
        return contactWrapper.field("lastname", ContactProvider::getLastname, ContactProvider::setLastname);
    }

    public StringProperty companynameProperty() {
        return contactWrapper.field("companyname", ContactProvider::getCompanyname, ContactProvider::setCompanyname);
    }

    public StringProperty phonenumberProperty() {
        return contactWrapper.field("phonenumber", ContactProvider::getPhonenumber, ContactProvider::setPhonenumber);
    }

    public StringProperty streetProperty() {
        return contactWrapper.field("street", ContactProvider::getStreet, ContactProvider::setStreet);
    }

    public StringProperty placeProperty() {
        return contactWrapper.field("place", ContactProvider::getPlace, ContactProvider::setPlace);
    }

    public StringProperty postcodeProperty() {
        return contactWrapper.field("postcode", ContactProvider::getPostcode, ContactProvider::setPostcode);
    }

    public StringProperty countryProperty() {
        return contactWrapper.field("country", ContactProvider::getCountry, ContactProvider::setCountry);
    }

    public StringProperty emailProperty() {
        return contactWrapper.field("email", ContactProvider::getEmail, ContactProvider::setEmail);
    }

}
