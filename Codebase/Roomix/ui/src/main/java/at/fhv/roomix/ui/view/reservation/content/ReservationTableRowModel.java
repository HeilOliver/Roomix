package at.fhv.roomix.ui.view.reservation.content;

import at.fhv.roomix.controller.reservation.model.ReservationPojo;
import at.fhv.roomix.ui.common.AbstractTableRowModel;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.content
 * ReservationTableRowModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationTableRowModel extends AbstractTableRowModel<ReservationPojo> {

    public ReservationTableRowModel(ReservationPojo pojo) {
        super(pojo);
    }

    public int getId() {
        return pojo.getId();
    }

    public String getFirstName() {
        return pojo.getContractingParty().getFirstName();
    }

    public String getLastName() {
        return pojo.getContractingParty().getLastName();
    }

    public String getCompanyName() {
        return pojo.getContractingParty().getCompanyName();
    }

    public String getComment() {
        return pojo.getComment() != null ?
                pojo.getComment().getComment() : "-";
    }
}
