package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.mappings.RoomCategoryMapping;
import at.fhv.roomix.persist.models.RoomCategoryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * RoomCategoryBuilder
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomCategoryBuilder {
    private final static Mapper mapper;

    static {
        mapper = Mapper.getInstance();
        mapper.addMapType(new RoomCategoryMapping(), RoomCategoryEntity.class, RoomCategory.class);
    }

    public static Collection<RoomCategory> getRoomCategories() throws BuilderLoadException {
        Collection<RoomCategoryEntity> all = null;
        try {
            all = RoomCategoryFactory.getInstance().getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        Collection<RoomCategory> results = new HashSet<>();
        for (RoomCategoryEntity entity : all) {
            results.add(mapper.map(entity, RoomCategory.class));
        }
        results.forEach((c) -> c.setUnitsLoader(ReservationUnitBuilder::getUnits));
        results.forEach((c) -> c.setPriceFinder(PriceBuilder::getPrice));
        results.forEach((c) -> c.setAgreementFinder(PartnerAgreementBuilder::getAgreement));
        return results;
    }

    public static RoomCategory getRoomCategory(int id) throws BuilderLoadException {
        RoomCategoryEntity entity = null;
        try {
            entity = RoomCategoryFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }

        RoomCategory category = mapper.map(entity, RoomCategory.class);
        category.setUnitsLoader(ReservationUnitBuilder::getUnits);
        category.setPriceFinder(PriceBuilder::getPrice);
        category.setAgreementFinder(PartnerAgreementBuilder::getAgreement);
        return category;
    }
}
