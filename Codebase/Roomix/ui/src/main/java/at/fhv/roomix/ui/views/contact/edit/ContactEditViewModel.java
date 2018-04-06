package at.fhv.roomix.ui.views.contact.edit;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Singleton;

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
    private BooleanProperty isValid = new SimpleBooleanProperty();

    private StringProperty firstname = new SimpleStringProperty();
    private StringProperty lastname = new SimpleStringProperty();
    private StringProperty companyname = new SimpleStringProperty();
    private StringProperty phonenumber = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty place = new SimpleStringProperty();
    private StringProperty postcode = new SimpleStringProperty();
    private StringProperty country = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();

    public BooleanProperty isValidProperty() {
        return isValid;
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

    public StringProperty phonenumberProperty() { return phonenumber; }

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
