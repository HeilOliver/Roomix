package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.CategoryDataPojo;
import at.fhv.roomix.domain.stay.CategoryStatus;

import java.util.Map;

/**
 * Roomix
 * at.fhv.roomix.implement.mapping
 * ${FILE_NAME}
 * 20/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class CategoryDataMapping implements MapType<CategoryStatus, CategoryDataPojo> {

    @Override
    public void map(CategoryStatus source, CategoryDataPojo destination, Mapper mapper) throws MappingException {
        destination.setDate(source.getDate());
        destination.setFree(source.getFree());
        destination.setOccupied(source.getOccupied());
        destination.setQuota(source.getQuota());
        destination.setUnconfirmedReservation(source.getUnconfirmed());
        destination.setPricePerDay(source.getPrice());
    }
}
