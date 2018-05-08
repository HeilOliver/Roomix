package at.fhv.roomix.ui.view.checkin.edit.unit;

import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItem;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItemViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class UnitView implements FxmlView<UnitViewModel> {

    @InjectViewModel
    private UnitViewModel viewModel;

    @FXML
    private ListView<PacketsItemViewModel> listArrangements;

    public void initialize(){
        viewModel.listChangedProperty().addListener((observable, oldValue, newValue) -> {
            listArrangements.setItems(viewModel.getArrangementList());
            listArrangements.setCellFactory(CachedViewModelCellFactory.createForFxmlView(PacketsItem.class));
        });
    }

    public void btn_commit() {

    }
}
