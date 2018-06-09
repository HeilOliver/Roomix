package at.fhv.roomix.ui.implement;

import at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentFactory;
import at.fhv.roomix.domain.implement.IReservation;
import at.fhv.roomix.domain.implement.IReservationUnit;
import at.fhv.roomix.domain.implement.IRoom;
import de.saxsys.mvvmfx.MvvmFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

public class RoomAssignmentController implements Observer {

    public TableView reservationUnits;
    private IReservation reservation;
    public TableView availableRooms;
    public Button assignRoom;
    public Button deleteRoom;

    RoomAssignmentController(IReservation reservation) {
        this.reservation = reservation;
    }

    @FXML
    protected void initialize() {
        loadRooms();
        loadReservationUnits();
        assignRoom.setDisable(true);
        deleteRoom.setDisable(true);
        reservationUnits.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> enableButton());
        availableRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> enableButton());
    }

    private void enableButton() {
        if (reservationUnits.getSelectionModel().getSelectedItem() != null && availableRooms.getSelectionModel().getSelectedItem() != null
                && ((IReservationUnit)reservationUnits.getSelectionModel().getSelectedItem()).getAssignedRoom() == null) {
            assignRoom.setDisable(false);
        }
        else {
            assignRoom.setDisable(true);
        }
        if (reservationUnits.getSelectionModel().getSelectedItem() != null && ((IReservationUnit)reservationUnits.getSelectionModel().getSelectedItem()).getAssignedRoom() != null) {
            deleteRoom.setDisable(false);
        }
        else {
            deleteRoom.setDisable(true);
        }
    }

    private void loadRooms() {
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();
        List<IRoom> rooms = roomAssignmentController.getRooms(reservation);
        rooms.forEach(room -> room.addObserver(this));
        ObservableList items = FXCollections.observableArrayList();
        items.addAll(rooms);
        availableRooms.setItems(items);
    }

    private void loadReservationUnits() {
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();
        List<IReservationUnit> reservationUnits = roomAssignmentController.getReservationUnitsByReservationId(reservation.getReservationID());
        reservationUnits.forEach(reservationUnit -> reservationUnit.addObserver(this));
        this.reservationUnits.getItems().addAll(reservationUnits);
    }

    @FXML
    private void saveRoomAssignment(ActionEvent actionEvent) throws IOException {
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();
        roomAssignmentController.saveRoomAssignments();
        MvvmFX.getNotificationCenter().publish(ImplementViewModel.changeToAllReservationCommand);
    }

    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();
        roomAssignmentController.rollback();
        MvvmFX.getNotificationCenter().publish(ImplementViewModel.changeToAllReservationCommand);

    }

    public void setReservation(IReservation reservation) {
        this.reservation = reservation;
    }

    @FXML
    private void assignRoom() {
        IRoom selectedRoom = (IRoom) availableRooms.getSelectionModel().getSelectedItem();
        IReservationUnit selectedReservationUnit = (IReservationUnit) reservationUnits.getSelectionModel().getSelectedItem();
        if (selectedRoom == null || selectedReservationUnit == null) return;
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();

        roomAssignmentController.assignRoom(selectedReservationUnit, selectedRoom);
        loadRooms();
    }

    @FXML
    private void deleteRoom() {
        IReservationUnit selectedItem = (IReservationUnit) reservationUnits.getSelectionModel().getSelectedItem();
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();

        roomAssignmentController.deleteAssignment(selectedItem);
        loadRooms();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        reservationUnits.refresh();
        loadRooms();
    }

    @FXML
    private void assignAutomatically(ActionEvent actionEvent) {
        // Todo: set application implement
        LinkedList<IReservation> reservations = new LinkedList<>();
        reservations.add(reservation);
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();

        roomAssignmentController.createRoomAssignmentsAutomatically(reservations, false);
    }

    @FXML
    private void deleteAllAssignments(ActionEvent actionEvent) {
        at.fhv.roomix.controller.implement.roomassignmentcontroller.RoomAssignmentController roomAssignmentController = RoomAssignmentFactory.getInstance();
        ((List<IReservationUnit>)reservationUnits.getItems()).forEach(reservationUnit -> {
            if (reservationUnit.getAssignedRoom() != null) {
                roomAssignmentController.deleteAssignment(reservationUnit);
            }
        });
        loadRooms();
    }


}
