package at.fhv.roomix.ui.views.login;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;

import javax.inject.Inject;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.views.login
 * LoginView
 * 06/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class LoginView implements FxmlView<LoginViewModel> {

    @InjectViewModel
    private LoginViewModel viewModel;

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnLogIn;

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();

    @Inject
    private ResourceBundle resourceBundle;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ProgressIndicator progressIndicator;

    public void initialize(){
        btnLogIn.disableProperty().bind(viewModel.logInPossibleProperty().not());
        btnLogOut.disableProperty().bind(viewModel.logOutPossibleProperty().not());

        txtUsername.disableProperty().bind(viewModel.logOutPossibleProperty());
        txtPassword.disableProperty().bind(viewModel.logOutPossibleProperty());

        progressIndicator.visibleProperty().bind(viewModel.inProcessProperty());

        txtPassword.textProperty().bindBidirectional(
                viewModel.passwordProperty()
        );

        txtUsername.textProperty().bindBidirectional(
                viewModel.usernameProperty()
        );

        validationVisualizer.initVisualization(viewModel.passwordValidation(), txtPassword);
        validationVisualizer.initVisualization(viewModel.usernameValidation(), txtUsername);

        viewModel.inErrorStateProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) return;

            Notifications.create()
                    .title(resourceBundle.getString("login.notification.title"))
                    .text(resourceBundle.getString("login.notification.loginFail"))
                    .position(Pos.BOTTOM_RIGHT)
                    .hideCloseButton()
                    .owner(mainPane)
                    .showError();
        });
    }

    @FXML
    private void btnLogOut_Click(ActionEvent actionEvent) {
        viewModel.logOut();
    }

    @FXML
    private void btnLogIn_Click(ActionEvent actionEvent) {
        viewModel.logIn();
    }
}
