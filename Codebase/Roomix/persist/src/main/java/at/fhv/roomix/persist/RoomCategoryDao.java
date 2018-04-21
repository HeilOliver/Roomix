package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.RoomCategoryEntity;

public class RoomCategoryDao extends AbstractDao<RoomCategoryEntity, Integer> {
    static {
        AbstractDao.addDao(RoomCategoryEntity.class, RoomCategoryDao::new);
    }

    private RoomCategoryDao() {
        super(RoomCategoryEntity.class);
    }


    public static RoomCategoryDao getInstance() {
        return new RoomCategoryDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Room Category DAO");
    }
}
