package at.fhv.roomix.ui.view.reservation.edit;

import at.fhv.roomix.controller.reservation.model.ReservationOptionPojo;
import at.fhv.roomix.ui.common.ViewHelper;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControl;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
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
    private Button btnAddContractingParty;
    @FXML
    private HBox boxContractingParty;
    @FXML
    private Button btnAddPerson;
    @FXML
    private ListView listPersons;
    @FXML
    private Button btnAddUnit;
    @FXML
    private ListView listUnits;
    @FXML
    private Button btnAddOption;
    @FXML
    private ListView<ItemControlViewModel> listOptions;
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

        listOptions.setItems(viewModel.getOptionControls());
        listOptions.setCellFactory(cellFactory);
        btnAddOption.disableProperty().bind(viewModel.isOptionAddAble().not());
    }

    @FXML
    private void addContractingParty(ActionEvent actionEvent) {
    }

    @FXML
    private void addPersonClick(ActionEvent actionEvent) {
    }

    @FXML
    private void addUnitClick(ActionEvent actionEvent) {
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
