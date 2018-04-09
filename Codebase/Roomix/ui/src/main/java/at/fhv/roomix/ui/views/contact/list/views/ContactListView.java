package at.fhv.roomix.ui.views.contact.list.views;

import at.fhv.roomix.ui.views.contact.list.ContactListTableModel;
import at.fhv.roomix.ui.views.contact.list.ContactListViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactListView
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactListView implements FxmlView<ContactListViewModel> {
    @FXML
    private TableView<ContactListTableModel> tab_contactTable;

    @InjectViewModel
    private ContactListViewModel viewModel;

    public void initialize() {
        tab_contactTable.setItems(viewModel.getContacts());
        viewModel.selectedTableRowProperty().bind(tab_contactTable.getSelectionModel().selectedItemProperty());
    }

}
