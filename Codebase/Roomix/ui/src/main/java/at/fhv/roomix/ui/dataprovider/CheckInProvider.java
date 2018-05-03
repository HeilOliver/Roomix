package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.stay.model.StayPojo;
import at.fhv.roomix.ui.common.ISearchAble;
import at.fhv.roomix.ui.common.validator.IUpdateAble;

public class CheckInProvider extends AbstractSearchEditProvider<StayPojo> {

    public CheckInProvider() {
        super(query -> {
           return null;
        }, update ->{

        });
    }
}
