package at.fhv.roomix.ui.view.reservation.content;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import org.controlsfx.control.MasterDetailPane;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.content
 * ReservationContentView
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationContentView implements FxmlView<ReservationContentViewModel> {

    @InjectViewModel
    private ReservationContentViewModel viewModel;

    @FXML
    private MasterDetailPane mdPane;

    public void initialize() {
        mdPane.showDetailNodeProperty().bind(viewModel.detailOpenProperty());
    }
}
