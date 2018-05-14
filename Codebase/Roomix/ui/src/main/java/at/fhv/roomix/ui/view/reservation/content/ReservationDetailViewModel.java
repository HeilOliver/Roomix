package at.fhv.roomix.ui.view.reservation.content;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.ui.view.reservation.scope.EDataProvider;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.content
 * ReservationDetailViewModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationDetailViewModel implements ViewModel {

    private StringProperty reservationID = new SimpleStringProperty();
    private StringProperty contractingPartyName = new SimpleStringProperty();
    private StringProperty reservationComment = new SimpleStringProperty();
    private StringProperty paymentType = new SimpleStringProperty();
    private StringProperty contractingPartyFname = new SimpleStringProperty();
    private StringProperty contractingPartyLname = new SimpleStringProperty();
    private StringProperty contractingPartyCompany = new SimpleStringProperty();
    private StringProperty contractingPartyPhone = new SimpleStringProperty();
    private ObservableList<String> persons = FXCollections.observableArrayList();
    private ObservableList<String> units = FXCollections.observableArrayList();

    @InjectScope
    private ReservationViewScope viewScope;

    private BooleanProperty detailAvailable = new SimpleBooleanProperty();

    public void initialize(){
        viewScope.selectedPojoProperty().addListener((observable, oldValue, newValue) -> {
            detailAvailable.setValue(newValue != null);
            reservationID.setValue(String.valueOf(newValue == null ? null : newValue.getId()));
            contractingPartyName.setValue(newValue == null ? null :
                    newValue.getContractingParty().getFirstName() + " " +
                    newValue.getContractingParty().getLastName() );
            reservationComment.setValue(newValue == null ? null :
                    (newValue.getReservationComment() == null ? "..." : newValue.getReservationComment()));
            // TODO: add payment type
            contractingPartyFname.setValue(newValue == null ? null : newValue.getContractingParty().getFirstName());
            contractingPartyLname.setValue(newValue == null ? null : newValue.getContractingParty().getLastName());
            contractingPartyCompany.setValue(newValue == null ? null : newValue.getContractingParty().getCompanyName());
            contractingPartyPhone.setValue(newValue == null ? null : newValue.getContractingParty().getPhoneNumber());
            Collection<String> personList = new LinkedList<>();
            Collection<String> unitList = new LinkedList<>();
            if (newValue != null) {
                if (newValue.getPersons() != null) {
                    newValue.getPersons().forEach(contactPojo -> {
                        personList.add(contactPojo.getForeName() + " " + contactPojo.getLastName());
                    });
                }
                if (newValue.getUnits() != null) {
                    newValue.getUnits().forEach(reservationUnitPojo -> {
                        unitList.add(reservationUnitPojo.getRoomCategory().getDescription() + ": " +
                                reservationUnitPojo.getStartDate().toString() + " - " + reservationUnitPojo.getEndDate().toString());
                    });
                }
            }
            persons.addAll(personList);
            units.addAll(unitList);
        });
    }


    public StringProperty reservationIDProperty() {
        return reservationID;
    }

    public StringProperty contractingPartyNameProperty() {
        return contractingPartyName;
    }

    public StringProperty reservationCommentProperty() {
        return reservationComment;
    }

    public BooleanProperty detailAvailableProperty() {
        return detailAvailable;
    }

    public StringProperty paymentTypeProperty() {
        return paymentType;
    }

    public StringProperty contractingPartyFnameProperty() {
        return contractingPartyFname;
    }

    public StringProperty contractingPartyLnameProperty() {
        return contractingPartyLname;
    }

    public StringProperty contractingPartyCompanyProperty() {
        return contractingPartyCompany;
    }

    public StringProperty contractingPartyPhoneProperty() {
        return contractingPartyPhone;
    }
    public ObservableList<String> getPersons() {
        return persons;
    }
    public ObservableList<String> getUnits() {
        return units;
    }
}
