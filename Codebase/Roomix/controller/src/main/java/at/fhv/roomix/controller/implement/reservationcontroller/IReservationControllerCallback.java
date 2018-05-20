package at.fhv.roomix.controller.implement.reservationcontroller;

import at.fhv.roomix.domain.implement.IReservation;

import java.util.List;

public interface IReservationControllerCallback {
    List<IReservation> getAllReservation();
}
