package at.fhv.roomix.ui.views.main.models;

import at.fhv.roomix.ui.views.contact.ContactView;
import at.fhv.roomix.ui.views.sidebar.SideBarItem;
import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main.models
 * PageProvider
 * 28/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class PageProvider {
    private static final ObservableList<SwitchablePage> topItem;
    private static final ObservableList<SwitchablePage> bottomItem;

    static {


        topItem = FXCollections.observableArrayList(
                new SwitchablePage("Sample", "BOOK", FluentViewLoader.fxmlView(ContactView.class).load().getView()),
                new SwitchablePage("HALLO", "BOOK", null),
                new SwitchablePage("TESTTEST", "BOOK", null)
        );
        bottomItem = FXCollections.observableArrayList(
                new SwitchablePage("Login", "BOOK", null),
                new SwitchablePage("About", "BOOK", null)
        );
    }

    public static ObservableList<SwitchablePage> getTopItem() {
        return topItem;
    }

    public static ObservableList<SwitchablePage> getBottomItem() {
        return bottomItem;
    }
}
