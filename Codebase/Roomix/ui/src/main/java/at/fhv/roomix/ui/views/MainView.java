package at.fhv.roomix.ui.views;

import at.fhv.roomix.ui.views.sidebar.SideBarItemView;
import at.fhv.roomix.ui.views.sidebar.SideBarItemViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

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
    private BorderPane pane_main;

    public void initialize(){
        pane_main.centerProperty().bind(viewModel.currentViewProperty());
    }
}
