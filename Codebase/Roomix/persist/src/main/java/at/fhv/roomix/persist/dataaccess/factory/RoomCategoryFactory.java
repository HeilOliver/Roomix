package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.RoomCategoryDao;
import at.fhv.roomix.persist.models.RoomCategoryEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * RoomCategoryFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomCategoryFactory extends EntityFactory<RoomCategoryEntity, Integer> {
    private static final Object lock = new Object();
    private static RoomCategoryFactory instance;

    private RoomCategoryFactory() {
        super(RoomCategoryDao::new, 1);
    }

    public static RoomCategoryFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new RoomCategoryFactory();
            }
        }
        return instance;
    }
}
