package at.fhv.roomix.ui.view.reservation.edit.item;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

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
    @FXML
    private Rectangle selected;
    @FXML
    private Button deleteEntryButton;
    @FXML
    private Label checkInCheckMark;

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();


    public void initialize() {
        lblContentBoxDescription.textProperty().bind(viewModel.contentTextProperty());
        selected.visibleProperty().bind(viewModel.isSelectedProperty());
        deleteEntryButton.visibleProperty().bind(viewModel.showDeleteButtonProperty());
        checkInCheckMark.visibleProperty().bind(viewModel.checkInMarkProperty());

        validationVisualizer.initVisualization(
                viewModel.getValidationStatus(),
                lblContentBoxDescription
        );
    }

    @FXML
    private void deleteClicked(ActionEvent actionEvent) {
        viewModel.onDelete();
    }

    @FXML
    private void selectedClicked(MouseEvent mouseEvent) {
        viewModel.selectMe();
    }
}
