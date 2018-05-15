package at.fhv.roomix.ui.view.checkin.edit.contracting_party;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ContractingPartyView implements FxmlView<ContractingPartyViewModel> {

    @InjectViewModel
    private ContractingPartyViewModel viewModel;

    @FXML
    private Text txtContractingPartyFname;
    @FXML
    private Text txtContractingPartyLname;
    @FXML
    private Text txtCompanyName;
    @FXML
    private Text txtContactID;
    @FXML
    private Text txtContactStreet;
    @FXML
    private Text txtContactHouseNumber;
    @FXML
    private Text txtContactPostcode;
    @FXML
    private Text txtContactCity;
    @FXML
    private Text txtContactCountry;
    @FXML
    private Text txtContractingPartyType;
    @FXML
    private Text txtContactPhone;
    @FXML
    private Text txtContactEmail;

    public void initialize(){
        txtContractingPartyFname.textProperty().bind(viewModel.firstNameProperty());
        txtContractingPartyLname.textProperty().bind(viewModel.contractingPartyLnameProperty());
        txtCompanyName.textProperty().bind(viewModel.companyNameProperty());
        txtContactID.textProperty().bind(viewModel.contactIDProperty().asString());
        txtContactStreet.textProperty().bind(viewModel.contactStreetProperty());
        txtContactHouseNumber.textProperty().bind(viewModel.contactHouseNumberProperty());
        txtContactPostcode.textProperty().bind(viewModel.contactPostcodeProperty());
        txtContactCity.textProperty().bind(viewModel.contactCityProperty());
        txtContactCountry.textProperty().bind(viewModel.contactCountryProperty());
        txtContractingPartyType.textProperty().bind(viewModel.contractingPartyTypeProperty());
        txtContactPhone.textProperty().bind(viewModel.contactPhoneProperty());
        txtContactEmail.textProperty().bind(viewModel.contactEmailProperty());
    }
}
