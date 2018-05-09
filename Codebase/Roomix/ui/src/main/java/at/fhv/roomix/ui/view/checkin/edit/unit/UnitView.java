package at.fhv.roomix.ui.view.checkin.edit.unit;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItem;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItemViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.annotation.PostConstruct;


public class UnitView implements FxmlView<UnitViewModel> {

    @InjectViewModel
    private UnitViewModel viewModel;

    @FXML
    private ListView<PacketsItemViewModel<ArrangementPojo>> listArrangements;
    @FXML
    private ListView<PacketsItemViewModel<ContactPojo>> listPersonAssignments;
    @FXML
    private Text txtArrivalDate;
    @FXML
    private Text txtEndDate;
    @FXML
    private Text txtCategory;
    @FXML
    private Text txtArrivalTime;


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
        /* Inital change event to display the person list. */
        viewModel.fireChange();
    }

    public void btn_commit() {

    }
}
