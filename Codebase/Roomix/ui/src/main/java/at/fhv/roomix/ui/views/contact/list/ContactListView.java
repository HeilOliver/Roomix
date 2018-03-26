package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.ui.views.sidebar.SideBarHeaderViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactListView
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactListView implements FxmlView<ContactListViewModel>{
    @FXML
    private Button but_delete;
    @FXML
    private Button but_new;
    @FXML
    private Button but_edit;
    @FXML
    private TableView<ContactListTableModel> tab_contactTable;

    @InjectViewModel
    private ContactListViewModel viewModel;

    public void initialize(){
       tab_contactTable.setItems(viewModel.getContacts());
       viewModel.selectedTableRowProperty().bind(tab_contactTable.getSelectionModel().selectedItemProperty());

       but_delete.disableProperty().bind(viewModel.contactSelectedProperty().not());
       but_edit.disableProperty().bind(viewModel.contactSelectedProperty().not());
    }

    @FXML
    private void buttonEdit_Click(ActionEvent actionEvent) {

    }

    @FXML
    private void buttonNew_Click(ActionEvent actionEvent) {

    }

    @FXML
    private void buttonDelete_Click(ActionEvent actionEvent) {

    }
}
