package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ContractingPartyEntity;
import at.fhv.roomix.persist.model.InvoicePositionEntity;
import at.fhv.roomix.persist.model.ReservationEntity;
import at.fhv.roomix.persist.model.ReservationUnitEntity;
import org.hibernate.HibernateException;

import java.util.Collection;

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
        ContractingPartyEntity contractingParty = entity.getContractingPartyByContractingParty();
        saveUpdate(contractingParty);
        entity.setContractingPartyByContractingParty(contractingParty);
        session.save(entity);
        Collection<ReservationUnitEntity> reservationUnits = entity.getReservationUnitsByReservationId();
        reservationUnits.forEach(reservationUnitEntity -> {
                    reservationUnitEntity.setReservationByReservation(entity);
                    saveUpdate(reservationUnitEntity);
            }
        );
        saveCollection(entity.getInvoicePositionsByReservationId());
        saveUpdate(entity.getPaymentTypeByPaymentType());
        //saveUpdate(entity.getPersonReservationsByReservationId());
        saveUpdate(entity.getReservationOptionByReservationOption());
        session.getTransaction().commit();
    }

    private <V>void saveUpdate(V obj) {
        if (obj == null) return;
        session.saveOrUpdate(obj);
    }

    private <V> void saveCollection(Collection<V> collection){
        if(collection == null || collection.isEmpty()) return;
        for (V v : collection) {
            saveUpdate(v);
        }
    }



}
