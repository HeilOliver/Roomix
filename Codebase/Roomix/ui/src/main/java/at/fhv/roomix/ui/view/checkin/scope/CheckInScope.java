package at.fhv.roomix.ui.view.checkin.scope;

import at.fhv.roomix.controller.stay.model.StayPojo;
import at.fhv.roomix.ui.common.AbstractMasterEditScope;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.dataprovider.CheckInProvider;

public class CheckInScope extends AbstractMasterEditScope<StayPojo>{

    public CheckInScope() {
        super(StayPojo::new, new CheckInProvider());
    }

        public void setOnError(IErrorCall onError) {
            onSaveUpdateError = onError;
        }
}
