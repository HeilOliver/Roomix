package at.fhv.roomix.ui.views.contact.edit;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.validators.EmailValidator;
import at.fhv.roomix.ui.validators.PhoneValidator;
import at.fhv.roomix.ui.views.contact.ContactProvider;
import at.fhv.roomix.ui.views.contact.scope.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
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
    private final ReadOnlyBooleanProperty inProgress;
    private final ModelWrapper<ContactPojo> contactWrapper = new ModelWrapper<>();
    private final CompositeValidator formValidator = new CompositeValidator();
    private final Validator lastNameValidator;
    private final Validator phoneNumberValidator;
    private final Validator streetValidator;
    private final Validator emailValidator;
    private final Validator countryValidator;
    private final Validator postcodeValidator;
    private final Validator placeValidator;
    private final Validator houseNumberValidator;

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

        firstNameValidator
                = validatorBuilder(firstNameProperty(), "");

        lastNameValidator
                = validatorBuilder(lastNameProperty(), "");

        phoneNumberValidator
                = new PhoneValidator(phoneNumberProperty(), "");

        streetValidator = validatorBuilder(streetProperty(), "");

        placeValidator = validatorBuilder(placeProperty(), "");

        postcodeValidator = validatorBuilder(postcodeProperty(), "");

        countryValidator = validatorBuilder(countryProperty(), "");

        houseNumberValidator = validatorBuilder(houseNumberProperty(), "");

        emailValidator = new EmailValidator(emailProperty());

        formValidator.addValidators(
                firstNameValidator,
                lastNameValidator,
                phoneNumberValidator,
                streetValidator,
                placeValidator,
                postcodeValidator,
                countryValidator,
                emailValidator,
                inProcessValidator
        );
    }

    private Validator validatorBuilder(StringProperty property, String resourcePath) {
        //String localizedMessage = resourceBundle.getString(resourcePath);
        return new FunctionBasedValidator<>(
                property,
                string -> string != null && !string.trim().isEmpty(),
                ValidationMessage.error(resourcePath));
    }

    public void initialize() {
        contactWrapper.modelProperty().bind(
                scope.inEditProperty());
    }

    void resetForm() {
        contactWrapper.reset();
    }

    private void commitChanges() {
//        if (contactWrapper.get() == null) {
//            contactWrapper.set(new ContactPojo());
//        }
//        contactWrapper.commit();

        scope.publish(ContactViewScope.CLOSE);
    }

    ValidationStatus getFirstNameValidator() {
        return firstNameValidator.getValidationStatus();
    }

    ValidationStatus getLastNameValidator() {
        return lastNameValidator.getValidationStatus();
    }

    ValidationStatus getPhoneNumberValidator() {
        return phoneNumberValidator.getValidationStatus();
    }

    ValidationStatus getStreetValidator() {
        return streetValidator.getValidationStatus();
    }

    ValidationStatus getEmailValidator() {
        return emailValidator.getValidationStatus();
    }

    ValidationStatus getCountryValidator() {
        return countryValidator.getValidationStatus();
    }

    ValidationStatus getPostcodeValidator() {
        return postcodeValidator.getValidationStatus();
    }

    ValidationStatus getPlaceValidator() {
        return placeValidator.getValidationStatus();
    }

    ValidationStatus getHouseNumberValidator() {
        return houseNumberValidator.getValidationStatus();
    }

    IntegerProperty idProperty() {
        return contactWrapper.field("id",
                ContactPojo::getContactId, ContactPojo::setContactId);
    }

    StringProperty firstNameProperty() {
        return contactWrapper.field("firstName",
                ContactPojo::getFname, ContactPojo::setFname);
    }

    StringProperty lastNameProperty() {
        return contactWrapper.field(
                "lastName", ContactPojo::getLname,
                ContactPojo::setLname);
    }

    StringProperty companyNameProperty() {
        return contactWrapper.field(
                "companyname", ContactPojo::getCompanyName,
                ContactPojo::setCompanyName);
    }

    StringProperty phoneNumberProperty() {
        return contactWrapper.field(
                "phoneNumber", ContactPojo::getPhoneNumber,
                ContactPojo::setPhoneNumber);
    }

    StringProperty streetProperty() {
        return contactWrapper.field(
                "street", ContactPojo::getStreet,
                ContactPojo::setStreet);
    }

    StringProperty placeProperty() {
        return contactWrapper.field(
                "place", ContactPojo::getPlace,
                ContactPojo::setPlace);
    }

    StringProperty postcodeProperty() {
        return contactWrapper.field(
                "postcode", ContactPojo::getPostcode,
                ContactPojo::setPostcode);
    }

    StringProperty countryProperty() {
        return contactWrapper.field(
                "country", ContactPojo::getCountry,
                ContactPojo::setCountry);
    }

    StringProperty emailProperty() {
        return contactWrapper.field(
                "email", ContactPojo::getEmail,
                ContactPojo::setEmail);
    }

    StringProperty houseNumberProperty() {
        return contactWrapper.field(
                "houseNumber", ContactPojo::getHouseNumber,
                ContactPojo::setHouseNumber);
    }

    void discard() {
        resetForm();
        scope.publish(ContactViewScope.CLOSE);
    }

    void save(ICallable errorCallback) {
        if (!formValidator.getValidationStatus().isValid()) {
            return;
        }
        ContactPojo tempContactPojo = new ContactPojo();
        contactWrapper.copyValuesTo(tempContactPojo);

        ContactProvider instance = ContactProvider.getInstance();
        instance.saveOrUpdate(tempContactPojo,
                this::commitChanges, errorCallback);
    }

    ReadOnlyBooleanProperty inProgressProperty() {
        return inProgress;
    }

    ReadOnlyBooleanProperty savePossibleProperty() {
        return formValidator.getValidationStatus().validProperty();
    }

    public interface ICallable {
        void call();
    }

}
