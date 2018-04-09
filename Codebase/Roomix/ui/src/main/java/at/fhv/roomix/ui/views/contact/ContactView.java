package at.fhv.roomix.ui.views.contact;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact
 * ContactView
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactView implements FxmlView<ContactViewModel> {

    @FXML
    private Pane editView;
    @FXML
    private Pane listView;

    @InjectViewModel
    private ContactViewModel viewModel;

    public void initialize() {
        editView.visibleProperty()
                .bind(viewModel.listViewEnabledProperty().not());

        listView.visibleProperty()
                .bind(viewModel.editViewEnabledProperty().not());
    }
}
