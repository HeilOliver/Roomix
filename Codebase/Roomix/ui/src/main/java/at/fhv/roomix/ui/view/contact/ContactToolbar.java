package at.fhv.roomix.ui.view.contact;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactToolbar
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactToolbar implements FxmlView<ContactToolbarViewModel> {

    @InjectViewModel
    private ContactToolbarViewModel viewModel;

    @FXML
    private Button btnNew;
    @FXML
    private Button btnEdit;
    @FXML
    private ProgressIndicator icoLoadProcess;
    @FXML
    private TextField searchInput;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    public void initialize() {
        icoLoadProcess.visibleProperty().bind(viewModel.getInProcessProperty());

        searchInput.textProperty().bindBidirectional(viewModel.getSearchQueryProperty());
        searchInput.visibleProperty().bind(viewModel.inContentViewProperty());

        btnNew.visibleProperty().bind(viewModel.inContentViewProperty());
        btnNew.managedProperty().bind(viewModel.inContentViewProperty());

        btnEdit.visibleProperty().bind(viewModel.inContentViewProperty());
        btnEdit.managedProperty().bind(viewModel.inContentViewProperty());
        btnEdit.disableProperty().bind(viewModel.editAbleProperty().not());

        btnSave.visibleProperty().bind(viewModel.inEditViewProperty());
        btnSave.managedProperty().bind(viewModel.inEditViewProperty());
        btnSave.disableProperty().bind(viewModel.saveAbleProperty().not());

        btnCancel.visibleProperty().bind(viewModel.inEditViewProperty());
        btnCancel.managedProperty().bind(viewModel.inEditViewProperty());
    }

    @FXML
    private void buttonEdit_Click(ActionEvent actionEvent) {
        viewModel.onEdit();
    }

    @FXML
    private void buttonNew_Click(ActionEvent actionEvent) {
        viewModel.onNew();
    }

    @FXML
    private void buttonSave_Click(ActionEvent actionEvent) {
        viewModel.onSave();
    }

    @FXML
    private void buttonCancel_Click(ActionEvent actionEvent) {
        viewModel.onCancel();
    }
}
