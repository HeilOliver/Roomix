package at.fhv.roomix.controller.implement.adapter;

import at.fhv.roomix.domain.implement.IRoomCategory;
import at.fhv.roomix.domain.room.RoomCategory;

/**
 * Roomix
 * at.fhv.roomix.controller.implement.adapter
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomCategoryAdapter implements IRoomCategory {
    private final RoomCategory category;

    public RoomCategoryAdapter(RoomCategory category) {
        if (category == null) throw new IllegalArgumentException();
        this.category = category;
    }

    public RoomCategory getCategory() {
        return category;
    }

    @Override
    public int getRoomCategoryID() {
        return category.getId();
    }

    @Override
    public String getCategoryDescription() {
        return category.getDescription();
    }
}
