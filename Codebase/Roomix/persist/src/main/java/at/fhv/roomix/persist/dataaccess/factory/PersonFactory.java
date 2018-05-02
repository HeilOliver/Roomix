package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.IDao;
import at.fhv.roomix.persist.dataaccess.dao.PersonDao;
import at.fhv.roomix.persist.models.PersonEntity;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.factory
 * PersonFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersonFactory extends EntityFactory<PersonEntity, Integer> {

    private static final Object lock = new Object();
    private static PersonFactory instance;

        private PersonFactory() {
            super(PersonDao::new, 20);
        }

    public static PersonFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new PersonFactory();
            }
        }
        return instance;
    }
}
