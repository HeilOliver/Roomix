package at.fhv.roomix.ui.views.contact.edit;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

import javax.inject.Inject;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.edit
 * ContactEditView
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactEditView implements FxmlView<ContactEditViewModel>{

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    @Inject
    private ResourceBundle resourceBundle;

    @InjectViewModel
    private ContactEditViewModel viewModel;
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

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();
    @FXML
    private AnchorPane mainPane;

    @FXML
    private ProgressIndicator icoSaveProcess;


    public void initialize() {
        firstnameInput.textProperty().bindBidirectional(viewModel.firstNameProperty());

        validationVisualizer.initVisualization(viewModel.getFirstNameValidator(), firstnameInput);
        icoSaveProcess.visibleProperty().bind(viewModel.inProgressProperty());

        btnSave.disableProperty().bind(viewModel.inProgressProperty());
        btnCancel.disableProperty().bind(viewModel.inProgressProperty());
    }

    @FXML
    private void buttonSave_Click(ActionEvent actionEvent) {
        viewModel.save(() ->
                Notifications.create()
                        .title(resourceBundle.getString("contact.notification.title"))
                        .text(resourceBundle.getString("contact.notification.saveFail"))
                        .position(Pos.BOTTOM_RIGHT)
                        .hideCloseButton()
                        .owner(mainPane)
                        .showError());
    }

    @FXML
    private void buttonCancel_Click(ActionEvent actionEvent) {
        viewModel.discard();
    }
}
