package at.fhv.roomix.ui;

import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.connector.ControllerFactory;
import at.fhv.roomix.ui.mocks.ReservationControllerMock;
import at.fhv.roomix.ui.views.MainView;
import at.fhv.roomix.ui.views.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

public class RunMe extends MvvmfxCdiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(RunMe.class);

    public static void main(String... args) {
        // TODO For Testing
        ReservationControllerFactory.InjectDependency(ReservationControllerMock::getInstance);

        Locale.setDefault(Locale.ENGLISH);
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
        LOG.info("Application Start");
        MvvmFX.setGlobalResourceBundle(resourceBundle);
        stage.setTitle(resourceBundle.getString("window.title"));

        ViewTuple<MainView, MainViewModel> main = FluentViewLoader.fxmlView(MainView.class).load();
        Scene rootScene = new Scene(main.getView());

        stage.setScene(rootScene);
        stage.show();
    }
}
