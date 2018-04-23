package at.fhv.roomix.ui.view.reservation.edit.contact;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.contact
 * ContactView
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactView implements FxmlView<ContactViewModel> {
    @InjectViewModel
    private ContactViewModel viewModel;
    @FXML
    private Button btnCommit;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnNew;
    @FXML
    private TextField searchInput;
    @FXML
    private AnchorPane viewTable;
    @FXML
    private AnchorPane viewEdit;

    public void initialize() {
        viewTable.visibleProperty().bind(viewModel.inEditViewProperty().not());
        viewEdit.visibleProperty().bind(viewModel.inEditViewProperty());
        searchInput.visibleProperty().bind(viewModel.inEditViewProperty().not());
        searchInput.textProperty().bindBidirectional(viewModel.searchQueryProperty());

//        btnNew.visibleProperty().bind(viewModel.inEditViewProperty().not());
//        btnNew.managedProperty().bind(viewModel.inEditViewProperty().not());
//
//        btnEdit.visibleProperty().bind(viewModel.inEditViewProperty().not());
//        btnEdit.disableProperty().bind(viewModel.isSelectedProperty().not());
//        btnCancel.visibleProperty().bind(viewModel.inEditViewProperty());
//        btnCancel.managedProperty().bind(viewModel.inEditViewProperty());
        btnEdit.setVisible(false);
        btnCancel.setVisible(false);
        btnNew.setVisible(false);
        btnEdit.setManaged(false);
        btnCancel.setManaged(false);
        btnNew.setManaged(false);

        btnCommit.disableProperty().bind(viewModel.isSelectedProperty().not());
        //btnCommit.visibleProperty().bind(viewModel.inEditViewProperty());
        //btnCommit.managedProperty().bind(viewModel.inEditViewProperty());
    }

    @FXML
    private void buttonNewClick(ActionEvent actionEvent) {
        viewModel.onNew();
    }

    @FXML
    private void buttonCancelClick(ActionEvent actionEvent) {
        viewModel.onCancel();
    }

    @FXML
    private void buttonEditClick(ActionEvent actionEvent) {
        viewModel.onEdit();
    }

    @FXML
    private void buttonCommitClick(ActionEvent actionEvent) {
        viewModel.onCommit();
    }
}
