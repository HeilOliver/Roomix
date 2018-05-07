package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.RoomCategoryEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * RoomCategoryDao
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomCategoryDao extends AbstractDao<RoomCategoryEntity, Integer> {
    public RoomCategoryDao() {
        super(RoomCategoryEntity.class);
    }
}
