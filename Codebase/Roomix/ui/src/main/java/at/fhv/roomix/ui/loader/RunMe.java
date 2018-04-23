package at.fhv.roomix.ui.loader;

import at.fhv.roomix.domain.session.configuration.Configuration;
import at.fhv.roomix.domain.session.configuration.IUiConfiguration;
import at.fhv.roomix.ui.common.CloseEvent;
import at.fhv.roomix.ui.common.StartEvent;
import at.fhv.roomix.ui.dataprovider.ConfigurationProvider;
import at.fhv.roomix.ui.dataprovider.LoginProvider;
import at.fhv.roomix.ui.view.main.MainView;
import at.fhv.roomix.ui.view.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui
 * Preloader
 * 14/04/2018 Oliver
 * <p>
 * The Main Class for starting the Application.
 */
public class RunMe extends MvvmfxCdiApplication {
    // Turn Debug to true if you want some awesome hacks
    private static final boolean DEBUG_INIT = true;
    private static final boolean DEBUG_SUPPRESS_START_EVENT = true;
    private static final boolean DEBUG_SUPPRESS_STOP_EVENT = false;

    private static final Logger LOG = LoggerFactory.getLogger(RunMe.class);

    @Inject
    private ResourceBundle resourceBundle;
    @Inject
    private Event<CloseEvent> onCloseEvent;
    @Inject
    private Event<StartEvent> onStartEvent;

    public static void main(String... args) {
        IUiConfiguration configuration = ConfigurationProvider.getConfiguration();
        if (configuration == null) {
            Locale.setDefault(Locale.ENGLISH);
        } else {
            String language = configuration.language();
            Locale locale = Locale.forLanguageTag(language);
            Locale.setDefault(locale);
        }
        launch(args);
    }

    @Override
    public void initMvvmfx() throws Exception {
        if (DEBUG_SUPPRESS_START_EVENT) return;
        onStartEvent.fire(new StartEvent());
    }

    @Override
    public void startMvvmfx(Stage stage) throws Exception {
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/roomix_icon.png")));
        LOG.info("Application Start");
        MvvmFX.setGlobalResourceBundle(resourceBundle);
        stage.setTitle(resourceBundle.getString("window.title"));

        ViewTuple<MainView, MainViewModel> main = FluentViewLoader.fxmlView(MainView.class).load();
        Scene rootScene = new Scene(main.getView());
        stage.setScene(rootScene);
        stage.setMinWidth(1000);
        stage.setMinHeight(400);
        stage.show();

        Platform.setImplicitExit(false);

        if (!DEBUG_INIT) return;
        LoginProvider
                .logIn("Sample", "0000", null);
    }

    @Override
    public void stopMvvmfx() throws Exception {
        if (DEBUG_SUPPRESS_STOP_EVENT) return;
        onCloseEvent.fire(new CloseEvent());
    }
}
