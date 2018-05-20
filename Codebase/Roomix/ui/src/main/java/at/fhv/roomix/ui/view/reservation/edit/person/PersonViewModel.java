package at.fhv.roomix.ui.view.reservation.edit.person;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.ui.common.validator.EmailValidator;
import at.fhv.roomix.ui.common.validator.PhoneValidator;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.FunctionBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import de.saxsys.mvvmfx.utils.validation.Validator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.person
 * PersonViewModel
 * 08/05/2018 Oliver
 * <p>
 * Enter Description here
 */
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

    public void initialize() {
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

    //region PersonProperties

    BooleanProperty vipProperty(){
        return pojoWrapper.field("vip",
                PersonPojo::isVip, PersonPojo::setVip);
    }

    BooleanProperty archiveProperty(){
        return pojoWrapper.field("archive",
                PersonPojo::isArchive, PersonPojo::setArchive);
    }

    StringProperty personFirstNameProperty() {
        return pojoWrapper.field("firstName",
                PersonPojo::getForeName, PersonPojo::setForeName);
    }

    StringProperty personLastNameProperty() {
        return pojoWrapper.field(
                "lastName", PersonPojo::getLastName,
                PersonPojo::setLastName);
    }
    private ObjectProperty<ContactPojo> contactPojoProperty() {
        return pojoWrapper.field("contact", PersonPojo::getContact, PersonPojo::setContact);
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


    public void onCommit() {
        if (contactFormValidator.getValidationStatus().isValid()) {
            ContactPojo contactPojo = new ContactPojo();
            contactWrapper.copyValuesTo(contactPojo);
            contactPojoProperty().setValue(contactPojo);
        }
        commit();
    }
}
