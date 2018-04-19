package at.fhv.roomix.persist;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * AbstractDao
 * 24/03/2018 OliverH
 * <p>
 * Abstract Data Access Object. Loads database "contents" over
 * a hibernate session. By inheriting from this class you can override
 * the methods with "internal" prefix so provide your own implementation of
 * how to load the data.
 */
public abstract class AbstractDao<T, PK extends Serializable> {
    protected static Logger daoLogger = Logger.getLogger(Logger.class);
    private static HashMap<Class, Supplier<AbstractDao>> supplierHashMap = new HashMap<>();
    protected Session session;
    private Class<T> type;

    AbstractDao(Class<T> type) {
        this.type = type;
        session = HibernateSessionFactory.getSession();
    }

    //public static AbstractDao<T, PK> getInstance();

    public static void addDao(Class contactEntityClass, Supplier<AbstractDao> supplier) {
        supplierHashMap.put(contactEntityClass, supplier);
    }

    public static <T extends AbstractDao> Supplier<AbstractDao> getDaoInstanceByEntityClass(Class type) {
        if (supplierHashMap.containsKey(type)) {
            return supplierHashMap.get(type);
        } else {
            daoLogger.info("No DAO Instance found for given key (Class type)");
            return null;
        }
    }

    public void dispose() {
        session.close();
        session = null;
    }

    public final void save(T entity) throws IllegalArgumentException, IllegalStateException, PersistSaveException {
        if (session == null) throw new IllegalStateException("No Session (null)");
        if (entity == null) throw new IllegalArgumentException("No Entity is provided");

        try {
            internalSave(entity);
        } catch (HibernateException e) {
            throw new PersistSaveException(e.getCause());
        }
    }

    protected void internalSave(T entity) throws HibernateException {
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

    public final T load(PK id) throws IllegalArgumentException, IllegalStateException, PersistLoadException {
        if (session == null) throw new IllegalStateException("No Session (null)");
        if (id == null) throw new IllegalArgumentException("No Id is provided");

        try {
            return internalLoad(id);
        } catch (HibernateException e) {
            throw new PersistLoadException(e.getCause());
        }
    }

    protected T internalLoad(PK id) throws HibernateException {
        return session.get(type, id);
    }

    public final List<T> loadAll() throws IllegalStateException, PersistLoadException {
        if (session == null) throw new IllegalStateException("Session Closed or Empty");

        try {
            return internalLoadAll();
        } catch (HibernateException e) {
            throw new PersistLoadException(e.getCause());
        }
    }

    protected List<T> internalLoadAll() throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);

        Root<T> from = query.from(type);
        CriteriaQuery<T> select = query.select(from);

        return session.createQuery(select).getResultList();
    }

    /**
     * Load a collection of entities where a foreign key (referencedColumn) matches the given key.
     * @param foreignKey Foreign key value for the entity the DAO provides
     * @param referencedColumn The database column name the given foreign key references
     * @return List of Entities where the condition is true
     * @throws PersistLoadException
     */
    public final List<T> loadByForeignKey(PK foreignKey, String referencedColumn)
            throws PersistLoadException, IllegalStateException {
        if (session == null) throw new IllegalStateException("Session Closed or Empty");

        try {
            return internalLoadByKey(foreignKey, referencedColumn);
        } catch (HibernateException e) {
            throw new PersistLoadException(e.getCause());
        }
    }

    protected List<T> internalLoadByKey(PK foreignKey, String referencedColumn){
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> from = query.from(type);
        CriteriaQuery<T> statement = query.select(from).where(builder.equal(from.get(referencedColumn), foreignKey));

        return session.createQuery(statement).getResultList();
    }
}
