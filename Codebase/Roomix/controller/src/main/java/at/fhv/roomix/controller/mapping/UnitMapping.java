package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.ArrangementPojo;
import at.fhv.roomix.controller.model.PricePojo;
import at.fhv.roomix.controller.model.ReservationUnitPojo;
import at.fhv.roomix.domain.reservation.Arrangement;
import at.fhv.roomix.domain.reservation.ReservationUnit;

import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.mapping
 * UnitMapping
 * 12/05/2018 Oliver
 * <p>
 *
 */
public class UnitMapping implements MapType<ReservationUnit, ReservationUnitPojo> {
    @Override
    public void map(ReservationUnit source, ReservationUnitPojo destination, Mapper mapper) throws MappingException {
        destination.setArrivalTime(source.getArrivalTime());
        destination.setEndDate(source.getEndDate());
        destination.setStartDate(source.getStartDate());
        destination.setId(source.getId());

        // TODO was machen wir hier?
        //destination.setRoomCategory();

        destination.setArrangements(new HashSet<>());
        for (Arrangement arrangement : source.getArrangements()) {
            ArrangementPojo arrangementPojo = new ArrangementPojo();
            arrangementPojo.setDescription(arrangement.getDescription());
            arrangementPojo.setId(arrangement.getId());
            PricePojo pricePojo = new PricePojo();
            pricePojo.setPrice(arrangement.getPrice());
            arrangementPojo.setPrice(pricePojo);
        }

    }
}
