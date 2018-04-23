package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ReservationUnitEntity;
import org.hibernate.HibernateException;

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

    @Override
    protected void internalSave(ReservationUnitEntity entity) throws HibernateException {
        session.beginTransaction();
        session.saveOrUpdate(entity.getReservationByReservation());
        session.saveOrUpdate(entity.getRoomCategoryByRoomCategory());
        session.saveOrUpdate(entity.getCancellationByCancellation());
        session.getTransaction().commit();
    }
}
