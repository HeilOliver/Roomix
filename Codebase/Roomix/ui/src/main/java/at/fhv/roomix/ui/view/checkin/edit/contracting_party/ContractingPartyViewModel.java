package at.fhv.roomix.ui.view.checkin.edit.contracting_party;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.mapping.accessorfunctions.StringGetter;
import de.saxsys.mvvmfx.utils.mapping.accessorfunctions.StringSetter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ResourceBundle;

public class ContractingPartyViewModel extends SubscribeAbleViewModel<ContactPojo> {
    @InjectScope
    private ReservationViewScope viewScope;
    @InjectResourceBundle
    private ResourceBundle bundle;

    private ModelWrapper<ContactPojo> contractingPartyWrapper = new ModelWrapper<>();

    public void initialize(){
        if(viewScope.selectedPojoProperty() != null){
            ReservationPojo reservationPojo = viewScope.selectedPojoProperty().get();
            contractingPartyWrapper.set(reservationPojo.getContractingParty());
        }
    }

    public StringProperty firstNameProperty() {
        return contractingPartyWrapper.field("firstName", ContactPojo::getFirstName, ContactPojo::setFirstName, "");
    }

    public StringProperty contractingPartyLnameProperty() {
        return contractingPartyWrapper.field("lastName", ContactPojo::getLastName, ContactPojo::setLastName, "");
    }

    public StringProperty companyNameProperty() {
        return contractingPartyWrapper.field("companyName", ContactPojo::getCompanyName, ContactPojo::setLastName, "");
    }

    public IntegerProperty contactIDProperty() {
        return contractingPartyWrapper.field("contactID", ContactPojo::getContactId, ContactPojo::setContactId, 0);
    }

    public StringProperty contactStreetProperty() {
        return contractingPartyWrapper.field("street", ContactPojo::getStreet, ContactPojo::setStreet, "");
    }

    public StringProperty contactHouseNumberProperty() {
        return contractingPartyWrapper.field("houseNumber", ContactPojo::getHouseNumber, ContactPojo::setHouseNumber, "");
    }

    public StringProperty contactPostcodeProperty() {
        return contractingPartyWrapper.field("postcode", ContactPojo::getPostcode, ContactPojo::setPostcode, "");
    }

    public StringProperty contactCityProperty() {
        return contractingPartyWrapper.field("city", ContactPojo::getPlace, ContactPojo::setPlace, "");
    }

    public StringProperty contactCountryProperty() {
        return contractingPartyWrapper.field("country", ContactPojo::getCountry, ContactPojo::setCountry, "");
    }

    public StringProperty contractingPartyTypeProperty() {
        return contractingPartyWrapper.field("type",
                (StringGetter<ContactPojo>) contactPojo -> ContractingPartyType.getByKey(contactPojo.getContractingPartyType(), bundle),
                (contactPojo, s) -> contactPojo.setContractingPartyType(ContractingPartyType.getByValue(s)), "");
    }

    public StringProperty contactPhoneProperty() {
        return contractingPartyWrapper.field("phone", ContactPojo::getPhoneNumber, ContactPojo::setPhoneNumber, "");
    }

    public StringProperty contactEmailProperty() {
        return contractingPartyWrapper.field("email", ContactPojo::getEmail, ContactPojo::setEmail, "");
    }
}
