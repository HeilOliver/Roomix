package at.fhv.roomix.ui.view.main.models;


import at.fhv.roomix.ui.dataprovider.LoginProvider;
import at.fhv.roomix.ui.view.login.LoginView;
import de.saxsys.mvvmfx.FluentViewLoader;
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
        topItem = FXCollections.observableArrayList();

        bottomItem = FXCollections.observableArrayList(
                new SwitchablePage("sdas", "dsds", LoginView.class)
        );
    }

    public static ObservableList<SwitchablePage> getTopItem() {
        return topItem;
    }

    public static ObservableList<SwitchablePage> getBottomItem() {
        return bottomItem;
    }
}
