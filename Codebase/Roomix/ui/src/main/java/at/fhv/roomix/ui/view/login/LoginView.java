package at.fhv.roomix.ui.view.login;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;

import javax.inject.Inject;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.view.login
 * LoginView
 * 14/04/2018 Oliver
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

    public void initialize() {
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

        Label lblName = new Label(resourceBundle.getString("login.notification.title"));
        Label lblStreet = new Label(resourceBundle.getString("login.notification.loginFail"));
        VBox vBox = new VBox(lblName, lblStreet);
        PopOver popOver = new PopOver(vBox);
        popOver.getRoot().getStylesheets().add("LoginStyle.css");

        viewModel.inErrorStateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                popOver.show(btnLogIn);
            } else {
                popOver.hide();
            }
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
