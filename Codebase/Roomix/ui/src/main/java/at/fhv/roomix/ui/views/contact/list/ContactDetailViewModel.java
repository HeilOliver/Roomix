package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.ui.views.contact.ContactProvider;
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
    private StringProperty street = new SimpleStringProperty();
    private StringProperty place = new SimpleStringProperty();
    private StringProperty postcode = new SimpleStringProperty();
    private BooleanProperty detailAvailable = new SimpleBooleanProperty();

    public ContactDetailViewModel() {
        ContactProvider.getInstance().selectedContactProperty()
                .addListener(((observable, oldValue, newValue) -> {
            detailAvailable.setValue(newValue != null);
            firstname.setValue(newValue == null ? null : newValue.getForename());
            lastname.setValue(newValue == null ? null : newValue.getSurname());
            companyname.setValue(newValue == null ? null : newValue.getCompanyName());
            street.setValue(newValue == null ? null : newValue.getStreet());
            place.setValue(newValue == null ? null : newValue.getPlace());
            postcode.setValue(newValue == null ? null : newValue.getPostcode());
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

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty placeProperty() {
        return place;
    }

    public StringProperty postcodeProperty() {
        return postcode;
    }
}
