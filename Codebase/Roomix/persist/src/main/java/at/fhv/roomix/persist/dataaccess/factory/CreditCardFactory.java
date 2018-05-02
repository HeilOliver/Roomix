package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.CreditCardDao;
import at.fhv.roomix.persist.models.CreditCardEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * CreditCardFactory
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CreditCardFactory extends EntityFactory<CreditCardEntity, Integer> {

    private static final Object lock = new Object();
    private static CreditCardFactory instance;

    private CreditCardFactory() {
        super(CreditCardDao::new, 20);

    }

    public static CreditCardFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new CreditCardFactory();
            }
        }
        return instance;
    }
}
