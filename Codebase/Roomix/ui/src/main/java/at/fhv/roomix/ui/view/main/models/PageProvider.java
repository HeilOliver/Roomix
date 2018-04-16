package at.fhv.roomix.ui.view.main.models;


import at.fhv.roomix.ui.view.about.AboutPage;
import at.fhv.roomix.ui.view.contact.ContactPage;
import at.fhv.roomix.ui.view.dashboard.DashBoardPage;
import at.fhv.roomix.ui.view.login.LoginPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main.models
 * PageProvider
 * 28/03/2018 OliverH
 * <p>
 * The Page provider
 */
public class PageProvider {
    private static final ObservableList<SwitchablePage> topItem;
    private static final ObservableList<SwitchablePage> bottomItem;

    static {
        topItem = FXCollections.observableArrayList(
                new DashBoardPage().getPage(),
                new ContactPage().getPage()
        );

        bottomItem = FXCollections.observableArrayList(
                new LoginPage().getPage(),
                new AboutPage().getPage()
        );
    }

    public static ObservableList<SwitchablePage> getTopItem() {
        return topItem;
    }

    public static ObservableList<SwitchablePage> getBottomItem() {
        return bottomItem;
    }
}
