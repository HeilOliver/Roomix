package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.FaultException;
import at.fhv.roomix.controller.model.CheckInPojo;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.controller.stay.StayControllerFactory;
import at.fhv.roomix.ui.common.ICallable;
import at.fhv.roomix.ui.common.IErrorCall;
import javafx.application.Platform;

public class CheckInProvider extends AbstractSearchEditProvider<ReservationPojo> {

    public CheckInProvider() {
        super(query -> StayControllerFactory.getInstance().getSearchedReservations(LoginProvider.getSessionID(), query),
                update ->{});
    }

    public void saveOrUpdate(ReservationPojo pojo, IErrorCall onError, ICallable onSuccess) {
        Platform.runLater(onSuccess::call);
    }
}
