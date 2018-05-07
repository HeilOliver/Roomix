package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistLoadException;
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
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<RoomCategoryEntity, RoomCategory>() {
            @Override
            protected void configure() {
                skip().setRooms(0);
            }
        });
    }

    private static RoomCategory fromEntity(RoomCategoryEntity entity) {
        RoomCategory category = mapper.map(entity, RoomCategory.class);
        category.setRooms(entity.getRooms().size());

        return category;
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
            results.add(fromEntity(entity));
        }
        return results;
    }

    public static RoomCategory getRoomCategory(int id) throws BuilderLoadException {
        RoomCategoryEntity entity = null;
        try {
            entity = RoomCategoryFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        return fromEntity(entity);
    }
}
