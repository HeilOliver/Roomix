package at.fhv.roomix.ui.view.checkin;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class CheckInView implements FxmlView<CheckInViewModel> {

    @InjectViewModel
    private CheckInViewModel viewModel;

    @FXML
    private AnchorPane contentView;
    @FXML
    private AnchorPane editView;

    public void initialize(){
        contentView.visibleProperty().bind(viewModel.contentViewVisibleProperty());
        editView.visibleProperty().bind(viewModel.editViewVisibleProperty());
    }
}
