package at.fhv.roomix.ui.view.checkin.edit.contracting_party;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.ui.view.checkin.edit.CheckInEditScope;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContractingPartyViewModel extends SubscribeAbleViewModel<ContactPojo> {
    @InjectScope
    private ReservationViewScope viewScope;

    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty contractingPartyLname = new SimpleStringProperty();
    private StringProperty companyName = new SimpleStringProperty();
    private StringProperty contactID = new SimpleStringProperty();
    private StringProperty contactStreet = new SimpleStringProperty();
    private StringProperty contactHouseNumber = new SimpleStringProperty();
    private StringProperty contactPostcode = new SimpleStringProperty();
    private StringProperty contactCity = new SimpleStringProperty();
    private StringProperty contactCountry = new SimpleStringProperty();
    private StringProperty contractingPartyType = new SimpleStringProperty();
    private StringProperty contactPhone = new SimpleStringProperty();
    private StringProperty contactEmail = new SimpleStringProperty();


    public void initialize(){
        if(viewScope.selectedPojoProperty() != null){
            ReservationPojo reservationPojo = viewScope.selectedPojoProperty().get();
            if(reservationPojo != null) {
                firstName.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getFirstName());
                contractingPartyLname.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getLastName());
                companyName.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getCompanyName());
                contactID.setValue(String.valueOf(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getContactId()));
                contactStreet.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getStreet());
                contactHouseNumber.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getHouseNumber());
                contactPostcode.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getPostcode());
                contactCity.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getPlace());
                contactCountry.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getCountry());
                // TODO: replace contracting party type with actual pojo
                contractingPartyType.setValue(String.valueOf(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getContractingPartyType()));
                contactPhone.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getPhoneNumber());
                contactEmail.setValue(reservationPojo.getContractingParty() == null ? null : reservationPojo.getContractingParty().getEmail());
            }
        }
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty contractingPartyLnameProperty() {
        return contractingPartyLname;
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public StringProperty contactIDProperty() {
        return contactID;
    }

    public StringProperty contactStreetProperty() {
        return contactStreet;
    }

    public StringProperty contactHouseNumberProperty() {
        return contactHouseNumber;
    }


    public StringProperty contactPostcodeProperty() {
        return contactPostcode;
    }

    public StringProperty contactCityProperty() {
        return contactCity;
    }

    public StringProperty contactCountryProperty() {
        return contactCountry;
    }

    public StringProperty contractingPartyTypeProperty() {
        return contractingPartyType;
    }

    public StringProperty contactPhoneProperty() {
        return contactPhone;
    }

    public StringProperty contactEmailProperty() {
        return contactEmail;
    }
}
