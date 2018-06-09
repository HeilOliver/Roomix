package at.fhv.roomix.ui.implement;

import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

/**
 * Roomix
 * at.fhv.roomix.ui.implement
 * ${FILE_NAME}
 * 07/06/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class ImplementViewModel implements ViewModel {
    public static String changeToAllReservationCommand = "ChangePage_AllReservation";
    public static String changeToAssignRoomCommand = "ChangePage_AssignRoom";

    private ObjectProperty<Parent> currentView = new SimpleObjectProperty<>();
    private Parent allReservationView;


    public ImplementViewModel() {
        try {
            URL url = getClass().getResource("/at/fhv/roomix/ui/implement/allReservation.fxml");

            allReservationView = FXMLLoader.load(url);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        currentView.setValue(allReservationView);
        MvvmFX.getNotificationCenter().subscribe(changeToAssignRoomCommand, (s, o) -> {
            if (o == null) return;
            if (o.length != 1) return;
            if (!(o[0] instanceof Parent)) return;

            Parent parent = (Parent) o[0];
            currentView.setValue(parent);
        });
        MvvmFX.getNotificationCenter().subscribe(changeToAllReservationCommand, (s, o) -> {
            currentView.setValue(allReservationView);
        });
    }

    ObjectProperty<Parent> currentViewProperty() {
        return currentView;
    }
}
