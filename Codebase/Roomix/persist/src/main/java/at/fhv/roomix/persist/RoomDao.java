package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.RoomEntity;

public class RoomDao extends AbstractDao<RoomEntity, Integer> {

    static {
        AbstractDao.addDao(RoomEntity.class, RoomDao::new);
    }

    private RoomDao() {
        super(RoomEntity.class);
    }

    public static RoomDao getInstance() {
        return new RoomDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Room DAO");
    }
}
