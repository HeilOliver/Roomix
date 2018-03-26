package at.fhv.roomix.ui.views.sidebar;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBarHeaderView
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarHeaderView implements FxmlView<SideBarHeaderViewModel> {

    @InjectViewModel
    private SideBarHeaderViewModel viewModel;
    @FXML
    private FontAwesomeIconView icon_menu;
    @FXML
    private FontAwesomeIconView icon_menuRotated;
    @FXML
    private Label lbl_header;

    public void initialize(){
        icon_menu.visibleProperty().bind(viewModel.getCollapsedState());
        icon_menuRotated.visibleProperty().bind(viewModel.getCollapsedState().not());
        lbl_header.textProperty().bind(viewModel.headerTextProperty());
    }

    @FXML
    private void ButtonMenue_Clicked(MouseEvent mouseEvent) {
        viewModel.switchMenuCollapsedState();
    }
}
