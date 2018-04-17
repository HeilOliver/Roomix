package at.fhv.roomix.ui.view.reservation.scope;


import at.fhv.roomix.controller.reservation.model.ReservationPojo;
import at.fhv.roomix.ui.common.AbstractMDScope;
import at.fhv.roomix.ui.dataprovider.ReservationProvider;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.scope
 * ReservationViewScope
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationViewScope extends AbstractMDScope<ReservationPojo> {

    public ReservationViewScope() {
        super(ReservationPojo::new, new ReservationProvider());
    }
}
