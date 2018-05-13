package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.model.CheckInPojo;

public class CheckInProvider extends AbstractSearchEditProvider<CheckInPojo> {

    public CheckInProvider() {
        super(query -> {
           return null;
        }, update ->{

        });
    }
}
