package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.InvoicePositionDao;
import at.fhv.roomix.persist.models.InvoicePositionEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * InvoicePositionFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class InvoicePositionFactory extends EntityFactory<InvoicePositionEntity, Integer> {

    private static final Object lock = new Object();
    private static InvoicePositionFactory instance;

    private InvoicePositionFactory() {
        super(InvoicePositionDao::new, 100);

    }

    public static InvoicePositionFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new InvoicePositionFactory();
            }
        }
        return instance;
    }
}
