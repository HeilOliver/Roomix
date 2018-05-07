package at.fhv.roomix.ui.view.checkin.content;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import org.controlsfx.control.MasterDetailPane;

public class CheckInContentView implements FxmlView<CheckInContentViewModel> {

    @InjectViewModel
    private CheckInContentViewModel viewModel;

    @FXML
    private MasterDetailPane masterDetailPane;

    public void initialize() {
        masterDetailPane.showDetailNodeProperty().bind(viewModel.detailOpenPropertyProperty());
    }

}
