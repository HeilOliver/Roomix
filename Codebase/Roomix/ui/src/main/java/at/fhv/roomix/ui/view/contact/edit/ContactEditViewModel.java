package at.fhv.roomix.ui.view.contact.edit;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.ui.common.validator.EmailValidator;
import at.fhv.roomix.ui.common.validator.PhoneValidator;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.edit
 * ContactEditViewModel
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactEditViewModel implements ViewModel {
    private final Validator firstNameValidator;
    private final ModelWrapper<ContactPojo> contactWrapper = new ModelWrapper<>();
    private final Validator lastNameValidator;
    private final Validator phoneNumberValidator;
    private final Validator streetValidator;
    private final Validator emailValidator;
    private final Validator countryValidator;
    private final Validator postcodeValidator;
    private final Validator placeValidator;
    private final Validator houseNumberValidator;
    private CompositeValidator formValidator = new CompositeValidator();
    @InjectScope
    private ContactViewScope viewScope;

    public ContactEditViewModel() {
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
                viewScope.inEditPojoProperty());

        viewScope.subscribe(ContactViewScope.commandEditView, ((key, payload) -> reLoad()));
        viewScope.subscribe(ContactViewScope.commandContentView, ((key, payload) -> contactWrapper.reset()));

        formValidator.addValidators(
                firstNameValidator,
                lastNameValidator,
                phoneNumberValidator,
                streetValidator,
                placeValidator,
                postcodeValidator,
                countryValidator,
                emailValidator
        );

        viewScope.inEditPropertyValidProperty().bind(
                formValidator.getValidationStatus().validProperty()
        );

        viewScope.subscribe(ContactViewScope.commandCommitEdit, (key, payload) -> {
            ContactPojo contactPojo = new ContactPojo();
            contactWrapper.copyValuesTo(contactPojo);
            contactPojo.setContactId(
                    viewScope.inEditPojoProperty().get().getContactId());
            viewScope.inEditPojoProperty().setValue(contactPojo);
        });
    }

    private void reLoad() {
        contactWrapper.reload();
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
                ContactPojo::getFirstName, ContactPojo::setFirstName);
    }

    StringProperty lastNameProperty() {
        return contactWrapper.field(
                "lastName", ContactPojo::getLastName,
                ContactPojo::setLastName);
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
}
