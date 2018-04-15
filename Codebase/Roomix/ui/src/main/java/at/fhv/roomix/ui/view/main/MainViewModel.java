package at.fhv.roomix.ui.view.main;

import at.fhv.roomix.ui.view.main.menuitem.SideBarItemViewModel;
import at.fhv.roomix.ui.view.main.models.PageProvider;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.itemlist.ListTransformation;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views
 * MainViewModel
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
public class MainViewModel implements ViewModel {
    private final ObjectProperty<Parent> currentView = new SimpleObjectProperty<>();

    private final StringProperty header = new SimpleStringProperty();
    private final BooleanProperty collapsed = new SimpleBooleanProperty();

    private final ObservableList<SideBarItemViewModel> topItems;
    private final ObservableList<SideBarItemViewModel> bottomItems;

    public MainViewModel() {
        // Add Listener for changing Pages
        SwitchablePage.selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.getCorrespondingPane() == null) {
                setToNone();
                return;
            }
            header.bind(newValue.headerProperty());
            currentView.setValue(newValue.getCorrespondingPane());
        }));

        // Transform all TopLists
        topItems = new ListTransformation<>(
                PageProvider.getTopItem(), s -> new SideBarItemViewModel(s, collapsed)
        ).getTargetList();

        // Transform all BottomLists
        bottomItems = new ListTransformation<>(
                PageProvider.getBottomItem(), s -> new SideBarItemViewModel(s, collapsed)
        ).getTargetList();

        // starting collapsed
        collapsed.setValue(true);
        setToNone();
    }

    private void setToNone() {
        header.unbind();
        header.setValue("-");
    }

    public ObservableList<SideBarItemViewModel> getTopItems() {
        return topItems;
    }

    public ObservableList<SideBarItemViewModel> getBottomItems() {
        return bottomItems;
    }

    public ObjectProperty<Parent> currentViewProperty() {
        return currentView;
    }

    public void switchMenuState() {
        boolean b = collapsed.get();
        collapsed.setValue(!b);
    }

    public StringProperty headerProperty() {
        return header;
    }
}
