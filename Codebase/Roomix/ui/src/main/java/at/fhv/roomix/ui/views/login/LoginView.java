package at.fhv.roomix.ui.views.login;

import at.fhv.roomix.ui.views.main.MainViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    public void initialize(){
        btnLogIn.disableProperty().bind(viewModel.loggedInProperty());
        txtUsername.disableProperty().bind(viewModel.loggedInProperty());
        txtPassword.disableProperty().bind(viewModel.loggedInProperty());
        btnLogOut.disableProperty().bind(viewModel.loggedInProperty().not());

        txtPassword.textProperty().bindBidirectional(
                viewModel.passwordProperty()
        );

        txtUsername.textProperty().bindBidirectional(
                viewModel.usernameProperty()
        );
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
