package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ReservationEntity;
import org.hibernate.HibernateException;

public class ReservationDao extends AbstractDao<ReservationEntity, Integer>{

    static {
        AbstractDao.addDao(ReservationEntity.class, ReservationDao::new);
    }

    ReservationDao(){
        super(ReservationEntity.class);
    }

    public ReservationDao getInstance(){
        return new ReservationDao();
    }

    @Override
    protected void internalSave(ReservationEntity entity) throws HibernateException {
        session.beginTransaction();
        session.saveOrUpdate(entity.getContractingpartyByContractingParty());
        session.saveOrUpdate(entity.getPersonByPerson());
        session.saveOrUpdate(entity.getInvoicepositionsByReservationId());
        session.saveOrUpdate(entity.getReservationoptionsByReservationId());
        session.saveOrUpdate(entity.getReservationunitsByReservationId());
        session.save(entity);
        session.getTransaction().commit();
    }

    public static void registerAtDao(){
        daoLogger.info("Registered at Reservation DAO");
    }

}
