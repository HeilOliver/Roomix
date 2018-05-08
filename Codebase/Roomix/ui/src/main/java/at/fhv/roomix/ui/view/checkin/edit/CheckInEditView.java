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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.MasterDetailPane;

public class CheckInEditView implements FxmlView<CheckInEditViewModel> {

    private static final double MINIMAL_DIVIDER_POSITION = 0.3D;

    @InjectViewModel
    private CheckInEditViewModel viewModel;

    @FXML
    private HBox boxContractingParty;
    @FXML
    private ListView<ItemControlViewModel> listPersons;
    @FXML
    private ListView<ItemControlViewModel> listUnits;
    @FXML
    private Button btnAddPerson;
    @FXML
    private HBox boxComment;
    @FXML
    private MasterDetailPane mdPane;
    @FXML
    private AnchorPane boxDetailPane;
    @FXML
    private HBox boxReservationOption;

    private CachedViewModelCellFactory<ItemControl, ItemControlViewModel> cellFactory
            = CachedViewModelCellFactory.createForFxmlView(ItemControl.class);

    public void initialize(){
        mdPane.dividerPositionProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() >= MINIMAL_DIVIDER_POSITION) return;
            // Let set the property later. If we not do this we get an exception.
            Platform.runLater(() -> mdPane.setDividerPosition(MINIMAL_DIVIDER_POSITION));
        }));

        viewModel.getCurrentDetailView().addListener(((observable, oldValue, newValue) -> {
            ViewHelper.setChildren(boxDetailPane, newValue);
        }));

        viewModel.getContractingPartyControl().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                boxContractingParty.getChildren().clear();
                return;
            }
            ViewHelper.setChildren(boxContractingParty, cellFactory.map(newValue).getView());
        }));

        viewModel.getOptionControl().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                boxReservationOption.getChildren().clear();
                return;
            }
            ViewHelper.setChildren(boxReservationOption, cellFactory.map(newValue).getView());
        });

        viewModel.getCommentControl().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                boxComment.getChildren().clear();
                return;
            }
            ViewHelper.setChildren(boxComment, cellFactory.map(newValue).getView());
        });

        listPersons.setItems(viewModel.getPersonControls());
        listPersons.setCellFactory(cellFactory);
        btnAddPerson.disableProperty().bind(viewModel.isPersonAddAble().not());

        listUnits.setItems(viewModel.getUnitControls());
        listUnits.setCellFactory(cellFactory);
    }

    @FXML
    private void addPersonClick() {
        viewModel.addPerson();
    }
    @FXML
    private void addComment() {
        viewModel.addComment();
    }
}
