package at.fhv.roomix.ui.views.sidebar;

import at.fhv.roomix.ui.views.contact.ContactView;
import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBareProvider
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBareProvider {

    private static final SideBareProvider SINGLETON = new SideBareProvider();
    private final BooleanProperty collapsed = new SimpleBooleanProperty(true);

    private final ObservableList<SideBarItem> items = FXCollections.observableArrayList();

    private SideBareProvider() {
        items.add(new SideBarItem("Contact", "BOOK",
                FluentViewLoader.fxmlView(ContactView.class).load().getView()));
        //items.add(new SideBarItem("Login", "ARCHIVE"));
    }

    public static SideBareProvider getInstance() {
        return SINGLETON;
    }

    public ObservableList<SideBarItem> getItems() {
        return items;
    }

    public BooleanProperty collapsedProperty() {
        return collapsed;
    }

    void switchCollapsedState() {
        boolean b = collapsed.get();
        collapsed.setValue(!b);
    }
}
