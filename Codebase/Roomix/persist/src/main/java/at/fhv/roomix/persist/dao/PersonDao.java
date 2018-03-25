package at.fhv.roomix.persist.dao;

import at.fhv.roomix.persist.exeption.PersistInternalException;
import at.fhv.roomix.persist.exeption.PersistSaveException;
import at.fhv.roomix.persist.model.PersonEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * PersonDao
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class PersonDao extends AbstractDao<PersonEntity, Integer> {
    private static final Object lock = new Object();
    private static PersonDao instance;

    private PersonDao() {
        super(PersonEntity.class);
    }

    public static PersonDao getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new PersonDao();
            }
        }
        return instance;
    }

    @Override
    public void save(Session session, PersonEntity entity) throws PersistInternalException, PersistSaveException {
        if (session == null) throw new PersistInternalException(new IllegalStateException("No Session"));
        if (entity == null) throw new PersistSaveException("No Entity is provided");

        try {
            session.beginTransaction();
            session.saveOrUpdate(entity.getContactByContact());
            session.save(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new PersistInternalException(e.getCause());
        }
    }
}
