package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.model.ReservationPojo;
import at.fhv.roomix.ui.common.ISearchAble;
import at.fhv.roomix.ui.common.validator.IUpdateAble;

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
                update -> ReservationControllerFactory
                        .getInstance().updateReservation(LoginProvider.getSessionID(), update)
        );
    }

}
