package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.PaymentTypeEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * PaymentTypeDao
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PaymentTypeDao extends AbstractDao<PaymentTypeEntity, Integer> {
    public PaymentTypeDao() {
        super(PaymentTypeEntity.class);
    }
}
