package at.fhv.roomix.ui.view.dashboard;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Roomix
 * at.fhv.roomix.ui.view.dashboard
 * DashBoardView
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class DashBoardView implements FxmlView<DashBoardViewModel> {

    @InjectViewModel
    private DashBoardViewModel viewModel;
    @FXML
    private Label lblTestLabel;

    public void initialize() {
        lblTestLabel.textProperty().bind(viewModel.mysteriesTextProperty());

    }
}
