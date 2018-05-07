package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.stay.model.StayPojo;

public class CheckInProvider extends AbstractSearchEditProvider<StayPojo> {

    public CheckInProvider() {
        super(query -> {
            return null;
        }, update -> {

        });
    }
}
