package at.fhv.roomix.ui.view.checkin.edit.person;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();
    @FXML
    private Button btnCommit;

    public void initialize(){
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

        validationVisualizer.initVisualization(
                viewModel.getFirstNameValidator(), inputContractingPartyFname);
        validationVisualizer.initVisualization(
                viewModel.getLastNameValidator(), inputContractingPartyLname);
        validationVisualizer.initVisualization(
                viewModel.getStreetValidator(), inputContactStreet);
        validationVisualizer.initVisualization(
                viewModel.getHouseNumberValidator(), inputContactHouseNumber);
        validationVisualizer.initVisualization(
                viewModel.getPostcodeValidator(), inputContactPostcode);
        validationVisualizer.initVisualization(
                viewModel.getPlaceValidator(), inputContactCity);
        validationVisualizer.initVisualization(
                viewModel.getCountryValidator(), inputContactCountry);
        validationVisualizer.initVisualization(
                viewModel.getPhoneNumberValidator(), inputContactPhone);
        validationVisualizer.initVisualization(
                viewModel.getEmailValidator(), inputContactEmail);
    }

    public void btn_commit() {
        viewModel.onCommit();
    }
}
