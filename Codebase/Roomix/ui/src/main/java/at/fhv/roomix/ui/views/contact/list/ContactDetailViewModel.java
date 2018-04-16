package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.ui.views.contact.scope.ContactMasterDetailScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactDetailViewModel
 * 27/03/2018 OliverH
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
    private ContactMasterDetailScope mdScope;

    public void initialize() {
        // TODO To Wrapper
        mdScope.selectedContactProperty()
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

    public BooleanProperty detailAvailableProperty() {
        return detailAvailable;
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty companynameProperty() {
        return companyname;
    }

    public StringProperty phonenumberProperty() {
        return phonenumber;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty placeProperty() {
        return place;
    }

    public StringProperty postcodeProperty() {
        return postcode;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty emailProperty() {
        return email;
    }
}
