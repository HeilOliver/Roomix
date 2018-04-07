package at.fhv.roomix.ui.views.contact.editcreate;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.editcreate
 * ContactEditCreateView
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactEditCreateView implements FxmlView<ContactEditCreateViewModel>{

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    @InjectViewModel
    private ContactEditCreateViewModel viewModel;
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
        firstnameInput.textProperty().bindBidirectional(viewModel.firstNameProperty());

    }

    @FXML
    private void buttonSave_Click(ActionEvent actionEvent) {
    }

    @FXML
    private void buttonCancel_Click(ActionEvent actionEvent) {
    }
}
