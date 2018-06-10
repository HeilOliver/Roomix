package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.domain.payment.RoomPrice;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.mappings.PriceMapping;
import at.fhv.roomix.persist.models.RoomCategoryEntity;
import at.fhv.roomix.persist.models.RoomCategoryPriceEntity;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.accessbuilder
 * ${FILE_NAME}
 * 20/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class PriceBuilder {
    private static Mapper mapper = Mapper.getInstance();

    static {
        mapper.addMapType(new PriceMapping(), RoomCategoryPriceEntity.class, RoomPrice.class);
    }


    public static RoomPrice getPrice(LocalDate date, RoomCategory roomCategory) {
        RoomCategoryEntity categoryEntity;
        try {
            categoryEntity = RoomCategoryFactory.getInstance().get(roomCategory.getId());
        } catch (PersistLoadException e) {
            throw new RuntimeException();
        }
        Optional<RoomCategoryPriceEntity> priceEntity = categoryEntity.getRoomCategoryPrices().stream()
                .filter((p) -> p.getSeason().getStartDate().toLocalDate().isBefore(date))
                .filter((p) -> p.getSeason().getEndDate().toLocalDate().isAfter(date))
                .findFirst();

        return priceEntity
                .map(roomCategoryPriceEntity -> mapper.map(roomCategoryPriceEntity, RoomPrice.class))
                .orElse(new RoomPrice());
    }
}
