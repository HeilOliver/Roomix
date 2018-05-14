package at.fhv.roomix.ui.view.checkin;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.Notifications;

public class CheckInView implements FxmlView<CheckInViewModel> {

    @InjectViewModel
    private CheckInViewModel viewModel;

    @FXML
    private AnchorPane contentView;
    @FXML
    private AnchorPane editView;
    @FXML
    private StackPane ParentPane;

    public void initialize(){
        contentView.visibleProperty().bind(viewModel.contentViewVisibleProperty());
        editView.visibleProperty().bind(viewModel.editViewVisibleProperty());
        viewModel.setInternalErrorCallBack(this::onInternalError);
        viewModel.setSaveUpdateErrorCallBack(this::onSaveUpdateError);
    }

    private void onInternalError(Error e) {
        Notifications.create()
                .title("Error")
                .text("An Internal Error Occurred. \n" +
                        "Please see Log for further Information \n"+
                        (e != null? e.getMessage() : ""))
                .showError();
    }

    private void onSaveUpdateError(Error e) {
        Notifications.create()
                .title("Error")
                .text("An Error Occurred while Saving the Unit \n" +
                        "Please see Log for further Information \n" +
                        (e != null? e.getMessage() : ""))
                .showError();
    }
}
