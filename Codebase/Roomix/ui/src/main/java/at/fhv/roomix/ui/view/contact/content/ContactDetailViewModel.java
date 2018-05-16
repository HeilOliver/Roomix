package at.fhv.roomix.ui.view.contact.content;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactDetailViewModel
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactDetailViewModel implements ViewModel {

    private ModelWrapper<ContactPojo> modelWrapper = new ModelWrapper<>();

    private BooleanProperty detailAvailable = new SimpleBooleanProperty();

    @InjectScope
    private ContactViewScope viewScope;

    public void initialize() {
        viewScope.selectedPojoProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    detailAvailable.setValue(newValue != null);
                    modelWrapper.set(newValue);
                    modelWrapper.reload();
                }));
    }

    BooleanProperty detailAvailableProperty() {
        return detailAvailable;
    }

    StringProperty firstnameProperty() {
        return modelWrapper.field("contactFirstName", ContactPojo::getFirstName, ContactPojo::setFirstName, "");
    }

    StringProperty lastnameProperty() {
        return modelWrapper.field("contactLastName", ContactPojo::getLastName, ContactPojo::setLastName, "");
    }

    StringProperty companynameProperty() {
        return modelWrapper.field("companyName", ContactPojo::getCompanyName, ContactPojo::setCompanyName, "");
    }

    StringProperty phonenumberProperty() {
        return modelWrapper.field("contactPhoneNumber", ContactPojo::getPhoneNumber, ContactPojo::setPhoneNumber, "");
    }

    StringProperty streetProperty() {
        return modelWrapper.field("contactStreet", ContactPojo::getStreet, ContactPojo::setStreet, "");
    }

    StringProperty placeProperty() {
        return modelWrapper.field("contactPlace", ContactPojo::getPlace, ContactPojo::setPlace, "");
    }

    StringProperty postcodeProperty() {
        return modelWrapper.field("contactPostCode", ContactPojo::getPostcode, ContactPojo::setPostcode, "");
    }

    StringProperty countryProperty() {
        return modelWrapper.field("contactCountry", ContactPojo::getCountry, ContactPojo::setCountry);
    }

    StringProperty emailProperty() {
        return modelWrapper.field("contactMail", ContactPojo::getEmail, ContactPojo::setEmail, "");
    }
}
