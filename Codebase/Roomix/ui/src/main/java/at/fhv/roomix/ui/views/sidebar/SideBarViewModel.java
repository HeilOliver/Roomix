package at.fhv.roomix.ui.views.sidebar;

import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.itemlist.ListTransformation;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBarViewModel
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
public class SideBarViewModel implements ViewModel {
    private final ListProperty<SideBarItemViewModel> items = new SimpleListProperty<>();
    private final ListTransformation<SideBarItem, SideBarItemViewModel> allItems;
    private final BooleanProperty collapsed;

    public SideBarViewModel() {
        collapsed = SideBareProvider.getInstance().collapsedProperty();
        allItems = new ListTransformation<>
                (SideBareProvider.getInstance().getItems(),
                        (s) -> new SideBarItemViewModel(s, collapsed));
        showLevelNone();
    }

    public BooleanProperty collapsedProperty() {
        return collapsed;
    }

    private void showLevelNone() {
        items.set(allItems.getTargetList());
    }

    public ObservableList<SideBarItemViewModel> itemsProperty() {
        return items;
    }
}
