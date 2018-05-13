package at.fhv.roomix.ui.view.checkin.scope;

import at.fhv.roomix.controller.model.CheckInPojo;
import at.fhv.roomix.ui.common.AbstractMasterEditScope;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.dataprovider.CheckInProvider;

public class CheckInScope extends AbstractMasterEditScope<CheckInPojo>{

    public CheckInScope() {
        super(CheckInPojo::new, new CheckInProvider());
    }
    public void setOnError(IErrorCall onError) {
            onSaveUpdateError = onError;
        }
}
