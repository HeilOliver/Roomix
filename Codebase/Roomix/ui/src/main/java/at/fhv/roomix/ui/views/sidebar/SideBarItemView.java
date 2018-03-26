package at.fhv.roomix.ui.views.sidebar;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBarItemView
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarItemView implements FxmlView<SideBarItemViewModel> {

    @InjectViewModel
    private SideBarItemViewModel viewModel;

    @FXML
    private Label lbl_tag;

    @FXML
    private FontAwesomeIconView icon;

    @FXML
    private HBox contentBox;

    @FXML
    private void selected(MouseEvent mouseEvent) {
        viewModel.selected();
    }

    public void initialize(){
        lbl_tag.textProperty().bind(viewModel.tagProperty());
        contentBox.visibleProperty().bind(viewModel.collapsedProperty().not());
        icon.glyphNameProperty().bind(viewModel.glyphProperty());
    }
}
