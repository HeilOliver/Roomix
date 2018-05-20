package at.fhv.roomix.ui.view.checkin.edit.person;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.ui.common.validator.EmailValidator;
import at.fhv.roomix.ui.common.validator.PhoneValidator;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.*;

public class PersonViewModel extends SubscribeAbleViewModel<PersonPojo> {
    private final ModelWrapper<ContactPojo> contactWrapper = new ModelWrapper<>();
    private final Validator firstNameValidator;
    private final Validator lastNameValidator;
    private final Validator phoneNumberValidator;
    private final Validator streetValidator;
    private final Validator emailValidator;
    private final Validator countryValidator;
    private final Validator postcodeValidator;
    private final Validator placeValidator;
    private final Validator houseNumberValidator;
    private CompositeValidator contactFormValidator = new CompositeValidator();

    @InjectScope
    private ReservationViewScope editScope;

    public PersonViewModel() {
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

    public void initialize(){
        contactWrapper.modelProperty().bindBidirectional(contactPojoProperty());

        contactFormValidator.addValidators(
                firstNameValidator,
                lastNameValidator,
                phoneNumberValidator,
                streetValidator,
                placeValidator,
                postcodeValidator,
                countryValidator,
                emailValidator
        );
    }

    StringProperty personFirstNameProperty() {
        return pojoWrapper.field("firstname", PersonPojo::getForeName, PersonPojo::setForeName);
    }

    StringProperty personLastNameProperty() {
        return pojoWrapper.field("lastname", PersonPojo::getLastName, PersonPojo::setLastName);
    }

    private ObjectProperty<ContactPojo> contactPojoProperty() {
        return pojoWrapper.field("contact", PersonPojo::getContact, PersonPojo::setContact);
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        contactWrapper.reset();
        contactWrapper.reload();
    }

    void onCommit(){
        if (contactFormValidator.getValidationStatus().isValid()) {
            ContactPojo contactPojo = new ContactPojo();
            contactWrapper.copyValuesTo(contactPojo);
            contactPojoProperty().setValue(contactPojo);
        }
        commit();
    }

    //region ContactValidationProperty
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
    //endregion

    //region ContactProperty's
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
    //endregion
}
