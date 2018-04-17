package at.fhv.roomix.ui.view.main.menuitem;

import at.fhv.roomix.ui.common.StringResourceResolver;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import javax.inject.Inject;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main.menuitem
 * SideBarItem
 * 28/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarItem implements FxmlView<SideBarItemViewModel> {

    @InjectViewModel
    private SideBarItemViewModel viewModel;

    @FXML
    private FontAwesomeIconView icon;


    @FXML
    private Label lbl_tag;
    @FXML
    private Rectangle spacer;
    @FXML
    private Rectangle selected;

    @Inject
    private ResourceBundle resourceBundle;

    public void initialize() {
        // lbl_tag.textProperty().bind(viewModel.tagProperty());
        // TODO:
        lbl_tag.textProperty().bind(StringResourceResolver.getAnonymousProperty(resourceBundle, viewModel.tagProperty()));
        icon.glyphNameProperty().bind(viewModel.glyphProperty());
        lbl_tag.visibleProperty().bind(viewModel.collapsedProperty().not());
        lbl_tag.managedProperty().bind(viewModel.collapsedProperty().not());

        spacer.visibleProperty().bind(viewModel.collapsedProperty().not());
        spacer.managedProperty().bind(viewModel.collapsedProperty().not());

        selected.visibleProperty().bind(viewModel.selectedProperty());
        selected.managedProperty().bind(viewModel.selectedProperty());
    }

    @FXML
    private void itemClicked(MouseEvent mouseEvent) {
        viewModel.clicked();
    }
}
