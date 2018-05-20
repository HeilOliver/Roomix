package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.model.CheckInPojo;
import at.fhv.roomix.controller.model.CheckInReply;
import at.fhv.roomix.controller.stay.IStayController;
import at.fhv.roomix.controller.stay.StayControllerFactory;
import at.fhv.roomix.ui.common.ICallableWithParameter;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnitCheckInProvider extends AbstractProvider {
    protected static final Logger LOG = LoggerFactory.getLogger(UnitCheckInProvider.class);


    public void doCheckIn(CheckInPojo pojo, ICallableWithParameter<CheckInReply> replyCallback, ICallableWithParameter<String> errorCallback){
        submit(() -> {
            IStayController controller = StayControllerFactory.getInstance();
            try {
                CheckInReply checkInReply = controller.setUnitsForCheckIn(LoginProvider.getSessionID(), pojo);
                Platform.runLater(() -> replyCallback.call(checkInReply));
            } catch (ArgumentFaultException | SessionFaultException | ValidationFault | CheckInException | SaveFault e) {
                LOG.debug(e.getMessage());
                Platform.runLater(() -> errorCallback.call(e.getMessage()));
            }
        });
    }
}
