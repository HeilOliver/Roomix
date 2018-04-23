package at.fhv.roomix.ui.view.reservation.scope;


import at.fhv.roomix.controller.reservation.model.ReservationPojo;
import at.fhv.roomix.ui.common.AbstractMasterEditScope;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.dataprovider.ReservationProvider;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.scope
 * ReservationViewScope
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationViewScope extends AbstractMasterEditScope<ReservationPojo> {
    public static final String commandPrint = "Command_Print";

    public ReservationViewScope() {
        super(ReservationPojo::new, new ReservationProvider());
    }

    public void setOnError(IErrorCall onError) {
        onSaveUpdateError = onError;
    }
}
