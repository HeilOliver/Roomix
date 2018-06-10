package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.ReservationOptionPojo;
import at.fhv.roomix.domain.reservation.ReservationOption;

/**
 * Roomix
 * at.fhv.roomix.implement.mapping
 * ReservationOptionMapping
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationOptionMapping implements MapType<ReservationOption, ReservationOptionPojo> {
    @Override
    public void map(ReservationOption source, ReservationOptionPojo destination, Mapper mapper) throws MappingException {
        destination.setOptionId(source.getId());
        destination.setOptionDescription(source.getDescription());
        // TODO was sollen wir hier machen???
        //destination.setOptionDueDate(source.getDaysBeforeArrival());
        //destination.setOptionFee(source.getFee());

        switch (source.getStatus()) {
            case PAYED:
                destination.setOptionStatus((byte) 1);
                break;
            case NEW:
                destination.setOptionStatus((byte) 2);
                break;
        }

    }
}
