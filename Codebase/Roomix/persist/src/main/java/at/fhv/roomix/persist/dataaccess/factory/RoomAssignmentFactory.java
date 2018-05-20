package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.domain.reservation.RoomAssignment;
import at.fhv.roomix.persist.dataaccess.IDao;
import at.fhv.roomix.persist.dataaccess.dao.RoomAssignmentDao;
import at.fhv.roomix.persist.models.RoomAssignmentEntity;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.factory
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomAssignmentFactory extends EntityFactory<RoomAssignmentEntity,Integer> {

    private static final Object lock = new Object();
    private static RoomAssignmentFactory instance;

        private RoomAssignmentFactory() {
            super(RoomAssignmentDao::new, 30);

        }

    public static RoomAssignmentFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new RoomAssignmentFactory();
            }
        }
        return instance;
    }
}
