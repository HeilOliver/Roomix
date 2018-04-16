package at.fhv.roomix.ui.view.contact.content;

import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
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

    private StringProperty firstname = new SimpleStringProperty();
    private StringProperty lastname = new SimpleStringProperty();
    private StringProperty companyname = new SimpleStringProperty();
    private StringProperty phonenumber = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty place = new SimpleStringProperty();
    private StringProperty postcode = new SimpleStringProperty();
    private StringProperty country = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private BooleanProperty detailAvailable = new SimpleBooleanProperty();

    @InjectScope
    private ContactViewScope viewScope;

    public void initialize() {
        // TODO To Wrapper
        viewScope.selectedPojoProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    detailAvailable.setValue(newValue != null);
                    firstname.setValue(newValue == null ? null : newValue.getFirstName());
                    lastname.setValue(newValue == null ? null : newValue.getLastName());
                    companyname.setValue(newValue == null ? null : newValue.getCompanyName());
                    phonenumber.setValue(newValue == null ? null : newValue.getPhoneNumber());
                    street.setValue(newValue == null ? null : newValue.getStreet());
                    place.setValue(newValue == null ? null : newValue.getPlace());
                    postcode.setValue(newValue == null ? null : newValue.getPostcode());
                    country.setValue(newValue == null ? null : newValue.getCountry());
                    email.setValue(newValue == null ? null : newValue.getEmail());
                }));
    }

    BooleanProperty detailAvailableProperty() {
        return detailAvailable;
    }

    StringProperty firstnameProperty() {
        return firstname;
    }

    StringProperty lastnameProperty() {
        return lastname;
    }

    StringProperty companynameProperty() {
        return companyname;
    }

    StringProperty phonenumberProperty() {
        return phonenumber;
    }

    StringProperty streetProperty() {
        return street;
    }

    StringProperty placeProperty() {
        return place;
    }

    StringProperty postcodeProperty() {
        return postcode;
    }

    StringProperty countryProperty() {
        return country;
    }

    StringProperty emailProperty() {
        return email;
    }
}
