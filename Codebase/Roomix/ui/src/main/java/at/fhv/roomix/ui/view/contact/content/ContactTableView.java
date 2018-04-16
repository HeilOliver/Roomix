package at.fhv.roomix.ui.view.contact.content;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactTableView
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactTableView implements FxmlView<ContactTableViewModel> {

    @InjectViewModel
    private ContactTableViewModel viewModel;
    @FXML
    private TableView<ContactTableRowModel> tabContactTable;


    public void initialize() {
        tabContactTable.setItems(viewModel.getContacts());
        viewModel.selectedTableRowProperty().bind(tabContactTable.getSelectionModel().selectedItemProperty());
    }
}
