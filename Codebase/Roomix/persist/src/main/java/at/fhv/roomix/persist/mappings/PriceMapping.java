package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.payment.RoomPrice;
import at.fhv.roomix.persist.models.RoomCategoryPriceEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * ${FILE_NAME}
 * 20/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class PriceMapping implements MapType<RoomCategoryPriceEntity, RoomPrice > {

    @Override
    public void map(RoomCategoryPriceEntity source, RoomPrice destination, Mapper mapper) throws MappingException {
        destination.setAcquisitionPrice(source.getAcquisitionPrice());
        destination.setDayPrice(source.getDayPrice());
        destination.setListPrice(source.getListPrice());
        destination.setMinimumPrice(source.getMinimumPrice());
    }
}
