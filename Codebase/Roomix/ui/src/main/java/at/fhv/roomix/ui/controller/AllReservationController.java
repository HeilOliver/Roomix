package at.fhv.roomix.ui.controller;

import at.fhv.roomix.controller.implement.reservationcontroller.ControllerFactory;
import at.fhv.roomix.controller.implement.reservationcontroller.IReservationController;
import at.fhv.roomix.domain.implement.IReservation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 *This class is the controllerclass for the fxml allReservation.
 */
public class AllReservationController {
    public DatePicker arrivalDatePicker;
    public Button manualAssigmentB;
    public Button autoAssignmentB;
    public Button editReservation;
    @FXML
    private TableView reservations;


    @FXML
    protected void initialize() {
        loadEntries();

        manualAssigmentB.setDisable(true);
        autoAssignmentB.setDisable(true);
        //editReservation.setDisable(true);

        reservations.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
           if (newSelection != null) {
                manualAssigmentB.setDisable(false);
               //editReservation.setDisable(false);
            } else {
                manualAssigmentB.setDisable(true);
               //editReservation.setDisable(true);
            }
       });
    }

    /**
     * Method to load all ReservationEntries
     */
    private void loadEntries() {
        IReservationController reservationController = ControllerFactory.getInstance().getReservationController();
        List<IReservation> allReservations = reservationController.getAllReservations();
        reservations.getItems().addAll(allReservations);
    }

    public void autoAssignmentingTheRooms(ActionEvent actionEvent) {
        ControllerFactory.getInstance().getRoomAssignmentController().createRoomAssignmentsAutomatically(reservations.getItems(), false);
    }

    public void manualAssignmentingTheRooms(ActionEvent actionEvent) throws IOException {
        IReservation selectedItem = (IReservation) reservations.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("roomAssignment.fxml"));
            Parent root = fxmlLoader.load();
            Scene startsite = new Scene(root, WindowSize.WIDTH, WindowSize.HEIGHT);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(startsite);
            stage.show();
        }
    }


}
