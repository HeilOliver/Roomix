package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.RoomAssignmentEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomAssignmentDao extends AbstractDao<RoomAssignmentEntity,Integer> {

    public RoomAssignmentDao() {
        super(RoomAssignmentEntity.class);
    }
}
