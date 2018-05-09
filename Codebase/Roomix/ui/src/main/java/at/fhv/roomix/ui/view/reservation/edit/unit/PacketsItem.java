package at.fhv.roomix.ui.view.reservation.edit.unit;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.unit
 * PacketsItem
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PacketsItem<T> implements FxmlView<PacketsItemViewModel<T>> {

    @InjectViewModel
    private PacketsItemViewModel<T> viewModel;

    @FXML
    private Label lblContent;
    @FXML
    private CheckBox checked;


    public void initialize() {
        lblContent.textProperty().bind(viewModel.contentProperty());
        checked.selectedProperty().bindBidirectional(viewModel.checkedProperty());
    }


}
