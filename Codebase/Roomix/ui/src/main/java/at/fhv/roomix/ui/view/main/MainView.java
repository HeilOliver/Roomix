package at.fhv.roomix.ui.view.main;

import at.fhv.roomix.ui.common.ViewHelper;
import at.fhv.roomix.ui.view.main.menuitem.SideBarItem;
import at.fhv.roomix.ui.view.main.menuitem.SideBarItemViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Roomix
 * at.fhv.roomix.ui.views
 * MainView
 * 24/03/2018 Oliver
 * <p>
 * Enter Description here
 */
public class MainView implements FxmlView<MainViewModel> {

    @InjectViewModel
    private MainViewModel viewModel;

    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label lbl_header;
    @FXML
    private VBox topBox;

    private CachedViewModelCellFactory<SideBarItem, SideBarItemViewModel> cellFactory;
    @FXML
    private VBox bottomBox;

    public void initialize() {
        cellFactory = CachedViewModelCellFactory.createForFxmlView(SideBarItem.class);

        viewModel.currentViewProperty().addListener(((observable, oldValue, newValue) -> {
            ViewHelper.setChildren(contentPane, newValue);
        }));
        ViewHelper.setChildren(contentPane, viewModel.currentViewProperty().get());

        viewModel.getTopItems().forEach((i) -> {
            Parent item = cellFactory.map(i).getView();
            topBox.getChildren().add(item);
        });

        viewModel.getBottomItems().forEach((i) -> {
            Parent item = cellFactory.map(i).getView();
            bottomBox.getChildren().add(item);
        });

        lbl_header.textProperty().bind(viewModel.headerProperty());
    }

    @FXML
    private void onMenuButton_Click(MouseEvent mouseEvent) {
        viewModel.switchMenuState();
    }
}
