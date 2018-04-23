package at.fhv.roomix.ui.view.dashboard;

import de.saxsys.mvvmfx.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.dashboard
 * DashBoardViewModel
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class DashBoardViewModel implements ViewModel {
    private StringProperty mysteriesText = new SimpleStringProperty();



    public DashBoardViewModel() {
        Thread thread = new Thread(() -> {
            String one = "HalloWelt";
            String two = "HalloRobert";

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> mysteriesText.setValue(one));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> mysteriesText.setValue(two));
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void initialize() {

    }

    public StringProperty mysteriesTextProperty() {
        return mysteriesText;
    }
}
