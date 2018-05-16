package at.fhv.roomix.ui.view.checkin.edit.option;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class OptionView implements FxmlView<OptionViewModel> {

    @InjectViewModel
    private OptionViewModel viewModel;

    @FXML
    private FontAwesomeIconView paid;
    @FXML
    private FontAwesomeIconView notpaid;
    @FXML
    private Text txtPaid;
    @FXML
    private Text txtNotPaid;
    @FXML
    private Text txtOptionDescription;
    @FXML
    private Text txtOptionFee;
    @FXML
    private Text txtOptionDueDate;

    public void initialize(){
        paid.visibleProperty().bind(viewModel.paidFlagProperty());
        txtPaid.visibleProperty().bind(viewModel.paidFlagProperty());
        txtNotPaid.visibleProperty().bind(viewModel.paidFlagProperty().not());
        notpaid.visibleProperty().bind(viewModel.paidFlagProperty().not());
        txtOptionDescription.textProperty().bind(viewModel.optionDescriptionProperty());
        txtOptionDueDate.textProperty().bind(viewModel.optionDueDateProperty());
        txtOptionFee.textProperty().bind(viewModel.optionFeeProperty());
    }

}
