package at.fhv.roomix.ui.view.reservation.edit.person;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.person
 * PersonView
 * 08/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersonView implements FxmlView<PersonViewModel> {

    @InjectViewModel
    private PersonViewModel viewModel;

    @FXML
    private TextField personFirstNameInput;
    @FXML
    private TextField personLastNameInput;
    @FXML
    private TextField inputContractingPartyFname;
    @FXML
    private TextField inputContractingPartyLname;
    @FXML
    private TextField inputCompanyName;
    @FXML
    private TextField inputContactStreet;
    @FXML
    private TextField inputContactHouseNumber;
    @FXML
    private TextField inputContactPostcode;
    @FXML
    private TextField inputContactCity;
    @FXML
    private TextField inputContactCountry;
    @FXML
    private TextField inputContactPhone;
    @FXML
    private TextField inputContactEmail;
    @FXML
    private CheckBox checkVip;
    @FXML
    private CheckBox checkArchive;

    public void initialize(){
        personFirstNameInput.textProperty().bindBidirectional(viewModel.firstNameProperty());
        personLastNameInput.textProperty().bindBidirectional(viewModel.lastNameProperty());
        personFirstNameInput.textProperty().bindBidirectional(viewModel.personFirstNameProperty());
        personLastNameInput.textProperty().bindBidirectional(viewModel.personLastNameProperty());

        inputContractingPartyFname.textProperty().bindBidirectional(viewModel.firstNameProperty());
        inputContractingPartyLname.textProperty().bindBidirectional(viewModel.lastNameProperty());
        inputCompanyName.textProperty().bindBidirectional(viewModel.companyNameProperty());
        inputContactStreet.textProperty().bindBidirectional(viewModel.streetProperty());
        inputContactHouseNumber.textProperty().bindBidirectional(viewModel.houseNumberProperty());
        inputContactPostcode.textProperty().bindBidirectional(viewModel.postcodeProperty());
        inputContactCity.textProperty().bindBidirectional(viewModel.placeProperty());
        inputContactCountry.textProperty().bindBidirectional(viewModel.countryProperty());
        inputContactPhone.textProperty().bindBidirectional(viewModel.phoneNumberProperty());
        inputContactEmail.textProperty().bindBidirectional(viewModel.emailProperty());

        checkVip.selectedProperty().bindBidirectional(viewModel.vipProperty());
        checkArchive.selectedProperty().bindBidirectional(viewModel.archiveProperty());
    }

    public void btn_commit() {
        viewModel.onCommit();
    }
}
