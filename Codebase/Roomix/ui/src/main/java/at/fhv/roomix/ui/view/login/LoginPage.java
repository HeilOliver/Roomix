package at.fhv.roomix.ui.view.login;

import at.fhv.roomix.ui.dataprovider.LoginProvider;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;

/**
 * Roomix
 * at.fhv.roomix.ui.view.login
 * LoginPage
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class LoginPage {
    private static final String DEFAULT_TAG = "Anmelden";
    private static final String DEFAULT_HEADER = "Anmelden";
    private final SwitchablePage page;

    public LoginPage() {
        page = new SwitchablePage(DEFAULT_TAG, "USER", LoginView.class);

        LoginProvider.currentSessionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                page.tagProperty().setValue(DEFAULT_TAG);
            } else {
                page.tagProperty().setValue(newValue.getName());
            }
        });
    }

    public SwitchablePage getPage() {
        return page;
    }
}
