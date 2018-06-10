package at.fhv.roomix.controller.implement.reservationcontroller;

import at.fhv.roomix.domain.implement.IReservation;

import java.util.List;

/**
 * THis implement is usecase based and used to control all methods specific for Reservations.
 */
public class ReservationController implements IReservationController {
    IReservationControllerCallback reservationControllerCallback;

    public ReservationController(IReservationControllerCallback reservationControllerCallback) {
        this.reservationControllerCallback = reservationControllerCallback;
    }

    /**
     * This method loads all Reservations from the database and checks if it already got a contracting party or a contact
     * And it fits it to UIReservations
     * @return
     */
    @Override
    public List<IReservation> getAllReservations() {
        return reservationControllerCallback.getAllReservation();
    }
}