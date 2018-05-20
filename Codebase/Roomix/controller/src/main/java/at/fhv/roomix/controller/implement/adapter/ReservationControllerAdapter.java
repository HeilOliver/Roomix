package at.fhv.roomix.controller.implement.adapter;

import at.fhv.roomix.controller.implement.reservationcontroller.IReservationControllerCallback;
import at.fhv.roomix.domain.implement.IReservation;
import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.persist.builder.accessbuilder.ReservationBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationControllerAdapter implements IReservationControllerCallback {
    @Override
    public List<IReservation> getAllReservation() {
        Collection<Reservation> all = null;
        try {
            all = ReservationBuilder.getAll();
        } catch (BuilderLoadException e) {
            // TODO LOG HERE
            e.printStackTrace();
            return new ArrayList<>();
        }
        return all.stream().map(ReservationAdapter::new).collect(Collectors.toList());
    }
}
