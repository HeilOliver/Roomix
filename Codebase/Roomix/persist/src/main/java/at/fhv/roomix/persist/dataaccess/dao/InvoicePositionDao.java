package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.InvoicePositionEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * InvoicePositionDao
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class InvoicePositionDao extends AbstractDao<InvoicePositionEntity, Integer> {

    public InvoicePositionDao() {
        super(InvoicePositionEntity.class);
    }

}
