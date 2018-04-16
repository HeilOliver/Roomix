package at.fhv.roomix.ui.views.contact.list.views;

import at.fhv.roomix.ui.views.contact.list.ContactDataViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import org.controlsfx.control.MasterDetailPane;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactDataView
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactDataView implements FxmlView<ContactDataViewModel> {
    @InjectViewModel
    private ContactDataViewModel viewModel;
    @FXML
    private MasterDetailPane mdPane;

    public void initialize() {
        mdPane.showDetailNodeProperty().bind(viewModel.detailOpenProperty());

    }
}