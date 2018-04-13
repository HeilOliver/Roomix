package at.fhv.roomix.ui.views.contact.list.views;

import at.fhv.roomix.ui.views.contact.list.ContactDataToolbarViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import javax.inject.Inject;
import java.util.ResourceBundle;

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

    @Inject
    private Stage primaryStage;

    @Inject
    private ResourceBundle resourceBundle;

    @InjectViewModel
    private ContactDataToolbarViewModel viewModel;

    @FXML
    private ProgressIndicator icoLoadProcess;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField searchInput;

    public void initialize() {
        btnArchive.disableProperty().bind(viewModel.contactSelectedProperty().not());
        btnEdit.disableProperty().bind(viewModel.contactSelectedProperty().not());

        btnSearch.disableProperty().bind(viewModel.inProcessProperty());
        icoLoadProcess.visibleProperty().bind(viewModel.inProcessProperty());

        searchInput.textProperty().bindBidirectional(viewModel.searchQueryProperty());
    }

    @FXML
    private void buttonEdit_Click(ActionEvent actionEvent) {
        viewModel.editContact();
    }

    @FXML
    private void buttonNew_Click(ActionEvent actionEvent) {
        viewModel.newContact();
    }

    @FXML
    private void buttonArchive_Click(ActionEvent actionEvent) {
        viewModel.archiveContact();
    }

    @FXML
    private void buttonSearch_Click(ActionEvent actionEvent) {
        // TODO resource adden
        viewModel.setErrorCall(() ->
                Notifications.create()
                        .title(resourceBundle.getString("contact.notification.title"))
                        .text(resourceBundle.getString("contact.notification.saveFail"))
                        .position(Pos.BOTTOM_RIGHT)
                        .hideCloseButton()
                        .showError());
    }
}
