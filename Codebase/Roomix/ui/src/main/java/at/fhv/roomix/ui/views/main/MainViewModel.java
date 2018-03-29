package at.fhv.roomix.ui.views.main;

import at.fhv.roomix.ui.views.contact.ContactView;
import at.fhv.roomix.ui.views.main.menuitem.SideBarMenuItemVM;
import at.fhv.roomix.ui.views.main.models.PageProvider;
import at.fhv.roomix.ui.views.main.models.SwitchablePage;
import at.fhv.roomix.ui.views.none.NoneView;
import at.fhv.roomix.ui.views.none.NoneViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
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
    private final Parent noneView = FluentViewLoader.fxmlView(NoneView.class).load().getView();
    private final BooleanProperty collapsed = new SimpleBooleanProperty();

    private final ObservableList<SideBarMenuItemVM> topItems;
    private final ObservableList<SideBarMenuItemVM> bottomItems;

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
                PageProvider.getTopItem(), s -> new SideBarMenuItemVM(s, collapsed)
        ).getTargetList();

        // Transform all BottomLists
        bottomItems = new ListTransformation<>(
                PageProvider.getBottomItem(), s -> new SideBarMenuItemVM(s, collapsed)
        ).getTargetList();

        // starting collapsed
        collapsed.setValue(true);
        setToNone();
    }

    private void setToNone() {
        currentView.setValue(noneView);
        header.unbind();
        header.setValue("-");
    }

    public ObservableList<SideBarMenuItemVM> getTopItems() {
        return topItems;
    }

    public ObservableList<SideBarMenuItemVM> getBottomItems() {
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
