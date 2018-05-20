package at.fhv.roomix.ui.view.checkin;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class CheckInToolbarView implements FxmlView<CheckInToolbarViewModel> {

    @InjectViewModel
    private CheckInToolbarViewModel viewModel;

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

    public void initialize(){
        icoLoadProcess.visibleProperty().bind(viewModel.getInProcessProperty());

        reservationSearchInput.textProperty().bindBidirectional(viewModel.getSearchQueryProperty());
        reservationSearchInput.visibleProperty().bind(viewModel.inContentViewProperty());

        btnStartCheckIn.visibleProperty().bind(viewModel.inContentViewProperty());
        btnStartCheckIn.disableProperty().bind(viewModel.editAbleProperty().not());
        btnStartCheckIn.managedProperty().bind(viewModel.inContentViewProperty());

        btnCancelCheckIn.visibleProperty().bind(viewModel.inEditViewProperty());
        btnCancelCheckIn.managedProperty().bind(viewModel.inEditViewProperty());

        btnDoCheckIn.visibleProperty().bind(viewModel.inEditViewProperty());
        btnDoCheckIn.managedProperty().bind(viewModel.inEditViewProperty());
    }

    @FXML
    private void btnStartCheckIn_OnClick(){
        viewModel.onEdit();
    }

    @FXML
    private void btnDoCheckIn_OnClick() { viewModel.onSave(); }

    @FXML
    private void btnCancelCheckIn_OnClick() { viewModel.onCancel(); }
}
