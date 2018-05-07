package at.fhv.roomix.ui.view.checkin.edit;

import at.fhv.roomix.ui.common.ViewHelper;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControl;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.controlsfx.control.MasterDetailPane;

public class CheckInEditView implements FxmlView<CheckInEditViewModel> {

    private static final double MINIMAL_DIVIDER_POSITION = 0.3D;

    @InjectViewModel
    private CheckInEditViewModel viewModel;

    @FXML
    private HBox boxContractingParty;
    @FXML
    private Button btnAddPerson;
    @FXML
    private HBox boxComment;
    @FXML
    private MasterDetailPane mdPane;

    private CachedViewModelCellFactory<ItemControl, ItemControlViewModel> cellFactory
            = CachedViewModelCellFactory.createForFxmlView(ItemControl.class);

    public void initialize(){
        mdPane.dividerPositionProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() >= MINIMAL_DIVIDER_POSITION) return;
            // Let set the property later. If we not do this we get an exception.
            Platform.runLater(() -> mdPane.setDividerPosition(MINIMAL_DIVIDER_POSITION));
        }));

        viewModel.getContractingPartyControl().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                boxContractingParty.getChildren().clear();
                return;
            }
            ViewHelper.setChildren(boxContractingParty, cellFactory.map(newValue).getView());
        }));
    }

    @FXML
    private void addPersonClick(ActionEvent actionEvent) {

    }
    @FXML
    private void addComment(ActionEvent actionEvent) {

    }
}
