package at.fhv.roomix.ui.view.reservation.edit;

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

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit
 * ReservationEditView
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationEditView implements FxmlView<ReservationEditViewModel> {

    private static final double MINIMAL_DIVIDER_POSITION = 0.3D;

    @InjectViewModel
    private ReservationEditViewModel viewModel;

    private CachedViewModelCellFactory<ItemControl, ItemControlViewModel> cellFactory
            = CachedViewModelCellFactory.createForFxmlView(ItemControl.class);
    @FXML
    private HBox boxContractingParty;
    @FXML
    private Button btnAddContractingParty;
    @FXML
    private ListView<ItemControlViewModel> listPersons;
    @FXML
    private Button btnAddPerson;
    @FXML
    private ListView<ItemControlViewModel> listUnits;
    @FXML
    private Button btnAddUnit;
    @FXML
    private Button btnAddOption;
    @FXML
    private HBox boxOption;
    @FXML
    private Button btnAddComment;
    @FXML
    private HBox boxComment;

    @FXML
    private AnchorPane boxDetailPane;
    @FXML
    private MasterDetailPane mdPane;


    public void initialize() {
        mdPane.dividerPositionProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() >= MINIMAL_DIVIDER_POSITION) return;
            // Let set the property later. If we not do this we get an exception.
            Platform.runLater(() -> mdPane.setDividerPosition(MINIMAL_DIVIDER_POSITION));
        }));

        viewModel.getCurrentDetailView().addListener(((observable, oldValue, newValue) -> {
            ViewHelper.setChildren(boxDetailPane, newValue);
        }));

        viewModel.getCommentControl().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                boxComment.getChildren().clear();
                return;
            }
            ViewHelper.setChildren(boxComment, cellFactory.map(newValue).getView());
        });
        btnAddComment.disableProperty().bind(viewModel.isCommendAddAble().not());

        viewModel.getContractingPartyControl().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                boxContractingParty.getChildren().clear();
                return;
            }
            ViewHelper.setChildren(boxContractingParty, cellFactory.map(newValue).getView());
        }));
        btnAddContractingParty.disableProperty().bind(viewModel.isCommendAddAble().not());

        viewModel.getOptionControl().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                boxOption.getChildren().clear();
                return;
            }
            ViewHelper.setChildren(boxOption, cellFactory.map(newValue).getView());
        }));
        btnAddOption.disableProperty().bind(viewModel.isOptionAddAble().not());

        listPersons.setItems(viewModel.getPersonControls());
        listPersons.setCellFactory(cellFactory);
        btnAddPerson.disableProperty().bind(viewModel.isPersonAddAble().not());

        listUnits.setItems(viewModel.getUnitControls());
        listUnits.setCellFactory(cellFactory);
        btnAddUnit.disableProperty().bind(viewModel.isUnitAddAble().not());
    }

    @FXML
    private void addContractingParty(ActionEvent actionEvent) {
        viewModel.addContractingParty();
    }

    @FXML
    private void addPersonClick(ActionEvent actionEvent) {
        viewModel.addPerson();
    }

    @FXML
    private void addUnitClick(ActionEvent actionEvent) {
        viewModel.addUnit();
    }

    @FXML
    private void addOptionClick(ActionEvent actionEvent) {
        viewModel.addOption();
    }

    @FXML
    private void addComment(ActionEvent actionEvent) {
        viewModel.addComment();
    }
}
