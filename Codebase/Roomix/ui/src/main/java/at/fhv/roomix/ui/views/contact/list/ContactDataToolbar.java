package at.fhv.roomix.ui.views.contact.list;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactDataToolbar
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactDataToolbar implements FxmlView<ContactDataToolbarViewModel> {
    @FXML
    private Button btnArchive;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnEdit;

    @InjectViewModel
    private ContactDataToolbarViewModel viewModel;

    public void initialize() {
        btnArchive.disableProperty().bind(viewModel.contactSelectedProperty().not());
        btnEdit.disableProperty().bind(viewModel.contactSelectedProperty().not());
        // TODO: btnNew.disabledProperty().bind(viewModel.contactSelectedProperty().not());
    }

    @FXML
    private void buttonEdit_Click(ActionEvent actionEvent) {

    }

    @FXML
    private void buttonNew_Click(ActionEvent actionEvent) {

    }

    @FXML
    private void buttonDelete_Click(ActionEvent actionEvent) {

    }
}
