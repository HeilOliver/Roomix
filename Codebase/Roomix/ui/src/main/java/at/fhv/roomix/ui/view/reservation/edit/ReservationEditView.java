package at.fhv.roomix.ui.view.reservation.edit;

import at.fhv.roomix.ui.view.reservation.edit.item.ItemControl;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit
 * ReservationEditView
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationEditView implements FxmlView<ReservationEditViewModel> {

    @InjectViewModel
    private ReservationEditViewModel viewModel;

    private CachedViewModelCellFactory<ItemControl, ItemControlViewModel> cellFactory
            = CachedViewModelCellFactory.createForFxmlView(ItemControl.class);

    @FXML
    private HBox boxContractingParty;
    @FXML
    private Button btnContractingParty;
    @FXML
    private AnchorPane boxDetailPane;

    private void setChildren(Parent parent) {
        boxDetailPane.getChildren().clear();
        if (parent == null) return;
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
        boxDetailPane.getChildren().add(parent);
    }

    public void initialize() {
        viewModel.getContractingParty().addListener(((observable, oldValue, newValue) -> {
            boxContractingParty.getChildren().clear();

            if(newValue == null) return;
            Parent view = cellFactory.map(newValue).getView();
            boxContractingParty.getChildren().add(view);
        }));

        btnContractingParty.disableProperty().bind(viewModel.getContractingParty().isNotNull());

        viewModel.getDetailView().addListener(((observable, oldValue, newValue) -> {
            setChildren(newValue);
        }));
    }

    @FXML
    private void addClicked(ActionEvent actionEvent) {
        viewModel.contractingPartyAdd();
    }


}
