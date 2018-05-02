package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.domain.reservation.ReservationOption;
import at.fhv.roomix.persist.dataaccess.IDao;
import at.fhv.roomix.persist.dataaccess.dao.ReservationOptionDao;
import at.fhv.roomix.persist.models.ReservationOptionEntity;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.factory
 * ReservationOptionFactory
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationOptionFactory extends EntityFactory<ReservationOptionEntity, Integer> {

    private static final Object lock = new Object();
    private static ReservationOptionFactory instance;

        private ReservationOptionFactory() {
            super(ReservationOptionDao::new, 30);

        }

    public static ReservationOptionFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationOptionFactory();
            }
        }
        return instance;
    }
}
