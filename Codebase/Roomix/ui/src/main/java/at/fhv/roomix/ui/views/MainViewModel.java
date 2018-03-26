package at.fhv.roomix.ui.views;

import at.fhv.roomix.ui.views.contact.ContactView;
import at.fhv.roomix.ui.views.none.NoneView;
import at.fhv.roomix.ui.views.none.NoneViewModel;
import at.fhv.roomix.ui.views.sidebar.SideBarItem;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

    private ObjectProperty<Parent> currentView = new SimpleObjectProperty<>();
    private Parent noneView = FluentViewLoader.fxmlView(NoneView.class).load().getView();

    public MainViewModel() {
        SideBarItem.selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                setToNoneView();
                return;
            }
            currentView.setValue(newValue.getParent());
        }));
        setToNoneView();
    }

    private void setToNoneView() {
        currentView.setValue(noneView);
    }

    public ObjectProperty<Parent> currentViewProperty() {
        return currentView;
    }
}
