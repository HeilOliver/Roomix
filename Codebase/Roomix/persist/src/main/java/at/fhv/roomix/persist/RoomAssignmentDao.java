package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.RoomAssignmentEntity;

public class RoomAssignmentDao extends AbstractDao<RoomAssignmentEntity, Integer> {

    static {
        AbstractDao.addDao(RoomAssignmentEntity.class, RoomAssignmentDao::new);
    }

    private RoomAssignmentDao() {
        super(RoomAssignmentEntity.class);
    }


    public static RoomAssignmentDao getInstance() {
        return new RoomAssignmentDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Room Assignment DAO");
    }
}
