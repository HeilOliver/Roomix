package at.fhv.roomix.ui.views.main.menuitem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main.menuitem
 * SideBarMenuItem
 * 28/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarMenuItem implements FxmlView<SideBarMenuItemVM> {

    @InjectViewModel
    private SideBarMenuItemVM viewModel;

    @FXML
    private FontAwesomeIconView icon;

    @FXML
    private Label lbl_tag;

    public void initialize() {
        lbl_tag.textProperty().bind(viewModel.tagProperty());
        icon.glyphNameProperty().bind(viewModel.glyphProperty());
        lbl_tag.visibleProperty().bind(viewModel.collapsedProperty().not());
        lbl_tag.managedProperty().bind(viewModel.collapsedProperty().not());
    }

    @FXML
    private void itemClicked(MouseEvent mouseEvent) {
        viewModel.clicked();
    }
}
