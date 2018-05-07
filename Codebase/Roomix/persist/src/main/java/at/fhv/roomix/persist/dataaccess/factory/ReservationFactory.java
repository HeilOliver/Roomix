package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.ReservationDao;
import at.fhv.roomix.persist.models.ReservationEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * ReservationFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationFactory extends EntityFactory<ReservationEntity, Integer> {
    private static final Object lock = new Object();
    private static ReservationFactory instance;

    private ReservationFactory() {
        super(ReservationDao::new, 40);
    }

    public static ReservationFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationFactory();
            }
        }
        return instance;
    }
}
