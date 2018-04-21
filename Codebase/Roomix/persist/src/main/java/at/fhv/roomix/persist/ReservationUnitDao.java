package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ReservationUnitEntity;

public class ReservationUnitDao extends AbstractDao<ReservationUnitEntity, Integer> {
    static {
        AbstractDao.addDao(ReservationUnitEntity.class, ReservationUnitDao::new);
    }

    private ReservationUnitDao() {
        super(ReservationUnitEntity.class);
    }


    public static ReservationUnitDao getInstance() {
        return new ReservationUnitDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Contact DAO");
    }
}
