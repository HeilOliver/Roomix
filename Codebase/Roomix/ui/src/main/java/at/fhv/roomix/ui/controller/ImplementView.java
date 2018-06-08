package at.fhv.roomix.ui.controller;

import at.fhv.roomix.ui.common.ViewHelper;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Roomix
 * at.fhv.roomix.ui.controller
 * ${FILE_NAME}
 * 07/06/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class ImplementView implements FxmlView<ImplementViewModel> {

    @InjectViewModel
    private ImplementViewModel viewModel;

    @FXML
    private AnchorPane anchorPane;


    public void initialize() {
        viewModel.currentViewProperty().addListener(((observable, oldValue, newValue) -> {
            ViewHelper.setChildren(anchorPane, newValue);
        }));

        ViewHelper.setChildren(anchorPane, viewModel.currentViewProperty().get());
    }
}
