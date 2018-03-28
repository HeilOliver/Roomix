package at.fhv.roomix.ui.views.sidebar;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBarView
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarView implements FxmlView<SideBarViewModel>{
    @FXML
    private ListView<SideBarItemViewModel> sideMenuItems;

    @InjectViewModel
    private SideBarViewModel viewModel;

    public void initialize(){

        ObservableList<SideBarItemViewModel> sideBarItemViewModels = viewModel.itemsProperty();
        viewModel.collapsedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                sideMenuItems.setMaxWidth(55);
            } else {
                sideMenuItems.setMaxWidth(300);
            }
        }));

        sideMenuItems.setItems(viewModel.itemsProperty());

    }
}
