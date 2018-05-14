package at.fhv.roomix.ui.view.checkin.content;

import at.fhv.roomix.controller.model.CheckInPojo;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.ui.common.AbstractTableRowModel;

public class CheckInTableRow extends AbstractTableRowModel<ReservationPojo> {

    public CheckInTableRow(ReservationPojo pojo){
        super(pojo);
    }

}
