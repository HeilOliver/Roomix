package at.fhv.roomix.ui.view.reservation;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation
 * ReservationView
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */

public class ReservationView implements FxmlView<ReservationViewModel> {
    @InjectViewModel
    private ReservationViewModel viewModel;
    @FXML
    private AnchorPane editView;
    @FXML
    private AnchorPane contentView;

    public void initialize() {
        editView.visibleProperty().bind(viewModel.editViewVisibleProperty());
        contentView.visibleProperty().bind(viewModel.contentViewVisibleProperty());
    }
}
