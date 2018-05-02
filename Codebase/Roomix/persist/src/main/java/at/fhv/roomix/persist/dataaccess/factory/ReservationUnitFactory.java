package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.ReservationUnitDao;
import at.fhv.roomix.persist.models.ReservationUnitEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * ReservationUnitFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnitFactory extends EntityFactory<ReservationUnitEntity, Integer> {

    private static final Object lock = new Object();
    private static ReservationUnitFactory instance;

    private ReservationUnitFactory() {
        super(ReservationUnitDao::new, 50);

    }

    public static ReservationUnitFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationUnitFactory();
            }
        }
        return instance;
    }
}
