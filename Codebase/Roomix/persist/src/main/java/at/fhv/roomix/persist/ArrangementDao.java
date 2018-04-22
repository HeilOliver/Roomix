package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ArrangementEntity;

public class ArrangementDao extends AbstractDao<ArrangementEntity, Integer> {

    static {
        AbstractDao.addDao(ArrangementEntity.class, ArrangementDao::new);
    }

    private ArrangementDao() {
        super(ArrangementEntity.class);
    }


    public static ArrangementDao getInstance() {
        return new ArrangementDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Arrangement DAO");
    }
}
