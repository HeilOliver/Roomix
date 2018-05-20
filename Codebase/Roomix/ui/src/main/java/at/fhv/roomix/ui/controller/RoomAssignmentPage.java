package at.fhv.roomix.ui.controller;

import at.fhv.roomix.ui.common.AbstractPage;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;
import at.fhv.roomix.ui.view.reservation.ReservationView;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class RoomAssignmentPage extends AbstractPage {
    private static final String DEFAULT_TAG = "common.RoomAssignment";
    private static final String DEFAULT_GLYPH = "FILE_TEXT";

    public RoomAssignmentPage() {
        super(new SwitchablePage(DEFAULT_TAG, DEFAULT_GLYPH, "/at/fhv/roomix/ui/controller/allReservation.fxml"));
    }
}
