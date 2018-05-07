package at.fhv.roomix.ui.view.checkin.content;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class CheckInTableView implements FxmlView<CheckInTableViewModel> {

    @InjectViewModel
    private CheckInTableViewModel viewModel;

    @FXML
    private TableView<CheckInTableRow> reservationTableView;

    public void initialize() {
        reservationTableView.setItems(viewModel.getReservations());
        viewModel.selectedTableRowProperty().bind(reservationTableView.getSelectionModel().selectedItemProperty());
    }
}
