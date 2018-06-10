package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.ArrangementPojo;
import at.fhv.roomix.controller.model.PricePojo;
import at.fhv.roomix.domain.reservation.Arrangement;

/**
 * Roomix
 * at.fhv.roomix.implement.mapping
 * ${FILE_NAME}
 * 21/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class ArrangementPojoMapping implements MapType<Arrangement, ArrangementPojo> {
    @Override
    public void map(Arrangement source, ArrangementPojo destination, Mapper mapper) throws MappingException {
        destination.setDescription(source.getDescription());
        destination.setId(source.getId());
        destination.setPrice(new PricePojo(source.getPrice()));
    }
}
