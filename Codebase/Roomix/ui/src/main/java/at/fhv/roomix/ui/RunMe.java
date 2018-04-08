package at.fhv.roomix.ui;

import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.ui.connector.ControllerFactory;
import at.fhv.roomix.controller.reservation.ReservationMock;
import at.fhv.roomix.ui.views.main.MainView;
import at.fhv.roomix.ui.views.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Locale;
import java.util.ResourceBundle;

public class RunMe extends MvvmfxCdiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(RunMe.class);

    public static void main(String... args) {
        Locale.setDefault(Locale.GERMANY);
        launch(args);
    }

    @Inject
    private ResourceBundle resourceBundle;

    @Override
    public void initMvvmfx() throws Exception {
        ControllerFactory.init();
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
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();
    }
}
