package at.fhv.roomix.ui.view.reservation.scope;


import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.ui.common.AbstractMasterEditScope;
import at.fhv.roomix.ui.common.IErrorCall;

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
    public static final String commandOnChange = "Command_on_change";
    public static final String commandOnCommit = "Command_commit";

    public ReservationViewScope() {
        super(ReservationPojo::new, null);
    }

    public void setOnError(IErrorCall onError) {
        onSaveUpdateError = onError;
    }

    public void init(EDataProvider providerType){
        provider = providerType.get();
    }
}
