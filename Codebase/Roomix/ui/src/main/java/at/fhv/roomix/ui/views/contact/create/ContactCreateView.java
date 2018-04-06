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
    }

    @FXML
    private void buttonSave_Click(ActionEvent actionEvent) {
    }

    @FXML
    private void buttonCancel_Click(ActionEvent actionEvent) {
    }
}
