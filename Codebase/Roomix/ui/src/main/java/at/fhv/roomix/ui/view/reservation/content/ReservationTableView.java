package at.fhv.roomix.ui.view.reservation.content;

import at.fhv.roomix.ui.view.contact.content.ContactTableRowModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.content
 * ReservationTableView
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationTableView implements FxmlView<ReservationTableViewModel> {

    @InjectViewModel
    private ReservationTableViewModel viewModel;

    @FXML
    private TableView<ReservationTableRowModel> tabContactTable;

    public void initialize() {
        tabContactTable.setItems(viewModel.getContacts());
        viewModel.selectedTableRowProperty().bind(tabContactTable.getSelectionModel().selectedItemProperty());
    }
}
