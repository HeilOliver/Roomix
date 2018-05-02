package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.PaymentTypeDao;
import at.fhv.roomix.persist.models.PaymentTypeEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * PaymentTypeFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PaymentTypeFactory extends EntityFactory<PaymentTypeEntity, Integer> {

    private static final Object lock = new Object();
    private static PaymentTypeFactory instance;

    private PaymentTypeFactory() {
        super(PaymentTypeDao::new, 1);

    }

    public static PaymentTypeFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new PaymentTypeFactory();
            }
        }
        return instance;
    }
}
