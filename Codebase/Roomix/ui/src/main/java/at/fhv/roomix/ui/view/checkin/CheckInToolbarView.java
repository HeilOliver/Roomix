package at.fhv.roomix.ui.view.checkin;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class CheckInToolbarView implements FxmlView<CheckInToolbarViewModel> {

    @InjectViewModel
    private CheckInToolbarViewModel checkInToolbarViewModel;

    @FXML
    private Button btnStartCheckIn;
    @FXML
    private Button btnDoCheckIn;
    @FXML
    private Button btnCancelCheckIn;
    @FXML
    private ProgressIndicator icoLoadProcess;
    @FXML
    private TextField reservationSearchInput;

    public void initialize() {
        icoLoadProcess.visibleProperty().bind(checkInToolbarViewModel.getInProcessProperty());

        reservationSearchInput.textProperty().bindBidirectional(checkInToolbarViewModel.getSearchQueryProperty());
        reservationSearchInput.visibleProperty().bind(checkInToolbarViewModel.inContentViewProperty());

        btnStartCheckIn.visibleProperty().bind(checkInToolbarViewModel.inContentViewProperty());
        btnStartCheckIn.managedProperty().bind(checkInToolbarViewModel.inContentViewProperty());

        btnCancelCheckIn.visibleProperty().bind(checkInToolbarViewModel.inEditViewProperty());
        btnCancelCheckIn.managedProperty().bind(checkInToolbarViewModel.inEditViewProperty());

        btnDoCheckIn.visibleProperty().bind(checkInToolbarViewModel.saveAbleProperty());
        btnDoCheckIn.visibleProperty().bind(checkInToolbarViewModel.saveAbleProperty());
    }

    @FXML
    private void btnStartCheckIn_OnClick() {
        checkInToolbarViewModel.onEdit();
    }

    @FXML
    private void btnDoCheckIn_OnClick() {
        checkInToolbarViewModel.onSave();
    }

    @FXML
    private void btnCancelCheckIn_OnClick() {
        checkInToolbarViewModel.onCancel();
    }
}
