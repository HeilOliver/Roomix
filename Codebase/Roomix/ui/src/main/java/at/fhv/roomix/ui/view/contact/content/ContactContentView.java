package at.fhv.roomix.ui.view.contact.content;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import org.controlsfx.control.MasterDetailPane;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactListView
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactContentView implements FxmlView<ContactContentViewModel> {

    @InjectViewModel
    private ContactContentViewModel viewModel;
    @FXML
    private MasterDetailPane mdPane;

    public void initialize() {
        mdPane.showDetailNodeProperty().bind(viewModel.detailOpenProperty());
    }
}
