package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.*;
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
        if(entity.getPersonReservationsByReservationId() != null) {
            for (PersonReservationEntity personReservationEntity : entity.getPersonReservationsByReservationId()) {
                saveUpdate(personReservationEntity.getPerson());
                personReservationEntity.setReservationByReservation(entity);
                saveUpdate(personReservationEntity);
            }
        }
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
