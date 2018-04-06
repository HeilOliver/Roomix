package at.fhv.roomix.ui.views.main.models;

import at.fhv.roomix.ui.config.ResourceHandler;
import at.fhv.roomix.ui.views.contact.ContactView;
import at.fhv.roomix.ui.views.login.LoginProvider;
import at.fhv.roomix.ui.views.login.LoginView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import java.util.*;
import org.apache.log4j.Logger;

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

    private static String getLocalizedString(String resourceName) {
        return ResourceHandler.getLocalizedString(resourceName);
    }

    static {
        topItem = FXCollections.observableArrayList(
                new SwitchablePage(getLocalizedString("main.contact"),
                        "BOOK", FluentViewLoader.fxmlView(ContactView.class).load().getView())
        );

        LoginProvider.getInstance();
        SwitchablePage user = new SwitchablePage("",
                "USER", FluentViewLoader.fxmlView(LoginView.class).load().getView());
        user.tagProperty().bindBidirectional(LoginProvider.getInstance().currentUserProperty());

        bottomItem = FXCollections.observableArrayList(
                user,
                new SwitchablePage(getLocalizedString("main.about"), "INFO_CIRCLE", null)
        );
    }

    public static ObservableList<SwitchablePage> getTopItem() {
        return topItem;
    }

    public static ObservableList<SwitchablePage> getBottomItem() {
        return bottomItem;
    }
}
