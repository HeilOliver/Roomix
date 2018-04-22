package at.fhv.roomix.ui.view.reservation.edit.option;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.option
 * OptionView
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class OptionView implements FxmlView<OptionViewModel> {

    @InjectViewModel
    private OptionViewModel viewModel;
    @FXML
    private Button btnCommit;
    @FXML
    private TextField txtDescriptionField;
    @FXML
    private DatePicker datePicker;

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();
    @FXML
    private TextField txtPrice;

    public void initialize() {
        txtDescriptionField.textProperty().bindBidirectional(viewModel.descriptionProperty());
        validationVisualizer.initVisualization(
                viewModel.descriptionValidation(), txtDescriptionField);

        datePicker.valueProperty().bindBidirectional(viewModel.dueDateProperty());
        validationVisualizer.initVisualization(
                viewModel.dueDateValidation(), datePicker);

        btnCommit.disableProperty().bind(viewModel.isCommitAble().not());

        txtPrice.textProperty().bindBidirectional(viewModel.downPriceProperty());
        validationVisualizer.initVisualization(
                viewModel.priceValidation(), txtPrice
        );
    }

    @FXML
    private void buttonCommit_Click(ActionEvent actionEvent) {
        viewModel.commitChange();
    }
}
