package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ReservationEntity;
import at.fhv.roomix.persist.model.ReservationUnitEntity;
import org.hibernate.HibernateException;

public class ReservationDao extends AbstractDao<ReservationEntity, Integer> {

    static {
        AbstractDao.addDao(ReservationEntity.class, ReservationDao::new);
    }

    ReservationDao() {
        super(ReservationEntity.class);
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Reservation DAO");
    }

    public ReservationDao getInstance() {
        return new ReservationDao();
    }

    @Override
    protected void internalSave(ReservationEntity entity) throws HibernateException {
        session.beginTransaction();
        session.save(entity);
        saveUpdate(entity.getContractingPartyByContractingParty());
        saveUpdate(entity.getInvoicePositionsByReservationId());
        saveUpdate(entity.getPaymentTypeByPaymentType());
        saveUpdate(entity.getPersonReservationsByReservationId());
        saveUpdate(entity.getReservationOptionByReservationOption());
        //session.save(entity);
        //session.merge("",entity.getReservationUnitsByReservationId());
        session.getTransaction().commit();
    }

    private <V>void saveUpdate(V obj) {
        if (obj == null) return;
        session.saveOrUpdate(obj);
    }



}
