package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.common.StringResourceResolver;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.item
 * ItemControl
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ItemControl implements FxmlView<ItemControlViewModel> {

    @InjectViewModel
    private ItemControlViewModel viewModel;

    @InjectResourceBundle
    private ResourceBundle bundle;

    @FXML
    private Label lblContentBoxDescription;

    public void initialize() {
        lblContentBoxDescription.textProperty().bind(
                StringResourceResolver.getAnonymousProperty(bundle, viewModel.contentTextProperty()));
    }

    @FXML
    private void deleteClicked(ActionEvent actionEvent) {
        viewModel.onDelete();
    }
}
