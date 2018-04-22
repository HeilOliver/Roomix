package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.RoomCategoryPriceEntity;

public class RoomCategoryPriceDao extends AbstractDao<RoomCategoryPriceEntity, Integer>{
    static {
        AbstractDao.addDao(RoomCategoryPriceEntity.class, RoomCategoryPriceDao::new);
    }

    private RoomCategoryPriceDao() {
        super(RoomCategoryPriceEntity.class);
    }

    public static RoomCategoryPriceDao getInstance() {
        return new RoomCategoryPriceDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Room Category Price DAO");
    }
}
