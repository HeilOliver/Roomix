package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.RoomEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * RoomDao
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomDao extends AbstractDao<RoomEntity, Integer> {

    public RoomDao() {
        super(RoomEntity.class);
    }
}
