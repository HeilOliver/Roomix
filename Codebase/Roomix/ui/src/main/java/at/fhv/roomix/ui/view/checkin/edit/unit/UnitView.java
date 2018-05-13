package at.fhv.roomix.ui.view.checkin.edit.unit;

import at.fhv.roomix.controller.model.ArrangementPojo;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItem;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItemViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.controlsfx.control.SegmentedBar;


public class UnitView implements FxmlView<UnitViewModel> {

    @InjectViewModel
    private UnitViewModel viewModel;

    @FXML
    private ListView<PacketsItemViewModel<ArrangementPojo>> listArrangements;
    @FXML
    private ListView<PacketsItemViewModel<PersonPojo>> listPersonAssignments;
    @FXML
    private Text txtArrivalDate;
    @FXML
    private Text txtEndDate;
    @FXML
    private Text txtCategory;
    @FXML
    private Text txtArrivalTime;
    @FXML
    private Text txtRoomNumber;
    @FXML
    private Button btnCommit;
    @FXML
    private SegmentedBar<SegmentedBar.Segment> segmentedBar;
    @FXML
    private Text txtCheckInRoomNumber;
    @FXML
    private Text txtRoomStatus;


    public void initialize(){
        viewModel.listChangedProperty().addListener((observable, oldValue, newValue) -> {
            listArrangements.setItems(viewModel.getArrangementList());
            listArrangements.setCellFactory(CachedViewModelCellFactory.createForFxmlView(PacketsItem.class));
        });
        viewModel.personListChangedProperty().addListener((observable, oldValue, newValue) -> {
            listPersonAssignments.setItems(viewModel.getPersonList());
            listPersonAssignments.setCellFactory(CachedViewModelCellFactory.createForFxmlView(PacketsItem.class));
        });
        txtArrivalDate.textProperty().bind(viewModel.arrivalDateProperty());
        txtEndDate.textProperty().bind(viewModel.endDateProperty());
        txtArrivalTime.textProperty().bind(viewModel.arrivalTimeProperty());
        txtCategory.textProperty().bind(viewModel.categoryProperty());
        btnCommit.disableProperty().bind(viewModel.commitButtonDisabledProperty());

        txtCheckInRoomNumber.textProperty().bind(viewModel.checkInRoomNumberProperty());
        txtRoomStatus.textProperty().bind(viewModel.statusTextProperty());
        txtCheckInRoomNumber.visibleProperty().bind(viewModel.showCheckInInformationProperty());
        txtRoomStatus.visibleProperty().bind(viewModel.showCheckInInformationProperty());
        /* Inital change event to display the person list. */
        viewModel.fireChange();
    }

    public void btn_commit() {
        viewModel.onCommit();
    }
}
