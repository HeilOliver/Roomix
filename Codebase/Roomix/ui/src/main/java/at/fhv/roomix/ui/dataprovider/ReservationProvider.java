package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.model.ReservationPojo;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * ReservationProvider
 * 16/04/2018 Oliver
 * <p>
 * The Data Provider for the Reservation Part.
 */
public class ReservationProvider extends AbstractSearchEditProvider<ReservationPojo> {

    public ReservationProvider() {
        super(query -> ReservationControllerFactory.getInstance()
                        .getSearchedReservation(LoginProvider.getSessionID(), query),
                update -> {
                    String pdfPath = ReservationControllerFactory
                            .getInstance().updateReservation(LoginProvider.getSessionID(), update);

                    if (pdfPath == null) return;

                    try {
                        Desktop.getDesktop().open(new File(pdfPath));
                    } catch (IOException ignored) { }
                }
        );
    }

}
