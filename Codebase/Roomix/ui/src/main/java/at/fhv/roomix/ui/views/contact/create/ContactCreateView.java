package at.fhv.roomix.ui.views.contact.create;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.create
 * ContactCreateView
 * 06.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ContactCreateView implements FxmlView<ContactCreateViewModel> {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    @InjectViewModel
    private ContactCreateViewModel viewModel;
    @FXML
    private TextField firstnameInput;
    @FXML
    private TextField lastnameInput;
    @FXML
    private TextField companynameInput;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private TextField streetInput;
    @FXML
    private TextField placeInput;
    @FXML
    private TextField postcodeInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField emailInput;


    public void initialize() {
        btnSave.disableProperty().bind(viewModel.isValidProperty().not());

        // TODO
/*        firstnameInput.textProperty().bindBidirectional(viewModel.firstnameProperty());
        lastnameInput.textProperty().bindBidirectional(viewModel.lastnameProperty());
        companynameInput.textProperty().bindBidirectional(viewModel.companynameProperty());
        phoneNumberInput.textProperty().bindBidirectional(viewModel.phonenumberProperty());
        streetInput.textProperty().bindBidirectional(viewModel.streetProperty());
        placeInput.textProperty().bindBidirectional(viewModel.placeProperty());
        postcodeInput.textProperty().bindBidirectional(viewModel.postcodeProperty());
        countryInput.textProperty().bindBidirectional(viewModel.countryProperty());
        emailInput.textProperty().bindBidirectional(viewModel.emailProperty());
*/
    }

    @FXML
    private void buttonSave_Click(ActionEvent actionEvent) {

    }

    @FXML
    private void buttonCancel_Click(ActionEvent actionEvent) {

    }
}
