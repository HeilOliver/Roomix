package at.fhv.roomix.ui.views.login;

import at.fhv.roomix.ui.views.main.MainViewModel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
    @FXML
    private BorderPane panLoggingIn;

    public void initialize(){
        btnLogIn.disableProperty().bind(viewModel.loggedInProperty());
        txtUsername.disableProperty().bind(viewModel.loggedInProperty());
        txtPassword.disableProperty().bind(viewModel.loggedInProperty());
        btnLogOut.disableProperty().bind(viewModel.loggedInProperty().not());
        panLoggingIn.visibleProperty().bind(viewModel.inProcessProperty());

        txtPassword.textProperty().bindBidirectional(
                viewModel.passwordProperty()
        );

        txtUsername.textProperty().bindBidirectional(
                viewModel.usernameProperty()
        );

        validationVisualizer.initVisualization(viewModel.passwordValidation(), txtPassword);
        validationVisualizer.initVisualization(viewModel.usernameValidation(), txtUsername);
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
