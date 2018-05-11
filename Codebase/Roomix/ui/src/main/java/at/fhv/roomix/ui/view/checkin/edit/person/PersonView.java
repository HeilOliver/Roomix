package at.fhv.roomix.ui.view.checkin.edit.person;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public void initialize(){
        personFirstNameInput.textProperty().bindBidirectional(viewModel.personFirstNameProperty());
        personLastNameInput.textProperty().bindBidirectional(viewModel.personLastnameProperty());
        inputContractingPartyFname.textProperty().bindBidirectional(viewModel.personFirstNameProperty());
        inputContractingPartyLname.textProperty().bindBidirectional(viewModel.personLastnameProperty());
    }

    public void btn_commit() {
        viewModel.onCommit();
    }
}
