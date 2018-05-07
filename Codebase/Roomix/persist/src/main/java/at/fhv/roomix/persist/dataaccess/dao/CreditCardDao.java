package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.CreditCardEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * CreditCardDao
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CreditCardDao extends AbstractDao<CreditCardEntity, Integer> {
    public CreditCardDao() {
        super(CreditCardEntity.class);
    }
}
