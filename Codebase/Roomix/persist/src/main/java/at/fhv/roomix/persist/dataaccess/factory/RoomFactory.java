package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.IDao;
import at.fhv.roomix.persist.dataaccess.dao.PaymentTypeDao;
import at.fhv.roomix.persist.dataaccess.dao.RoomDao;
import at.fhv.roomix.persist.models.RoomEntity;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.factory
 * RoomFactory
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomFactory extends EntityFactory<RoomEntity, Integer> {


    private static final Object lock = new Object();
    private static RoomFactory instance;

    private RoomFactory() {
        super(RoomDao::new, 1);
    }

    public static RoomFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new RoomFactory();
            }
        }
        return instance;
    }
}
