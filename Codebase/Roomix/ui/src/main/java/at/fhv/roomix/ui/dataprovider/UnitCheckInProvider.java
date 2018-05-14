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

public class UnitCheckInProvider extends AbstractProvider {

    private BooleanProperty inLoadCheckIn = new SimpleBooleanProperty();

    public void doCheckIn(CheckInPojo pojo, ICallableWithParameter<CheckInReply> replyCallback, ICallableWithParameter<String> errorCallback){
        submit(() -> {
            Platform.runLater(() -> inLoadCheckIn.setValue(true));
            IStayController controller = StayControllerFactory.getInstance();
            try {
                CheckInReply checkInReply = controller.setUnitsForCheckIn(LoginProvider.getSessionID(), pojo);
                if(checkInReply != null){
                    Platform.runLater(() -> replyCallback.call(checkInReply));
                } else {
                    errorCallback.call("Internal error");
                }
            } catch (ArgumentFaultException | SessionFaultException | ValidationFault | CheckInException | SaveFault e) {
                e.printStackTrace();
                errorCallback.call("Internal error");
            } finally {
                Platform.runLater(() -> inLoadCheckIn.setValue(true));
            }
        });
    }
}
