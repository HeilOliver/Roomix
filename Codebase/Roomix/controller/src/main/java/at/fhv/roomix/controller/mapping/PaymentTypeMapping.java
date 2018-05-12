package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.PaymentTypePojo;
import at.fhv.roomix.domain.reservation.PaymentType;

/**
 * Roomix
 * at.fhv.roomix.controller.mapping
 * PaymentTypeMapping
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PaymentTypeMapping implements MapType<PaymentType, PaymentTypePojo> {
    @Override
    public void map(PaymentType source, PaymentTypePojo destination, Mapper mapper) throws MappingException {
        destination.setDescription(source.getDescription());
        destination.setId(source.getId());
    }
}
