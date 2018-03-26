package at.fhv.roomix.ui.views.sidebar;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBarHeaderViewModel
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
public class SideBarHeaderViewModel implements ViewModel {
    private StringProperty headerText = new SimpleStringProperty();

    public SideBarHeaderViewModel() {
        SideBarItem.selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                headerText.setValue("BliBlaBlub");
                return;
            }
            headerText.setValue(newValue.tagProperty().get());
        }));
    }

    StringProperty headerTextProperty() {
        return headerText;
    }

    BooleanProperty getCollapsedState() {
        return SideBareProvider.getInstance().collapsedProperty();
    }

    void switchMenuCollapsedState() {
        SideBareProvider.getInstance().switchCollapsedState();
    }
}
