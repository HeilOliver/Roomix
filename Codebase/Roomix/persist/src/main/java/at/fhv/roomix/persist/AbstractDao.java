package at.fhv.roomix.persist;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * AbstractDao
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public abstract class AbstractDao<T, PK extends Serializable> {
    private Class<T> type;
    protected Session session;

    AbstractDao(Class<T> type) {
        this.type = type;
        session = HibernateSessionFactory.getSession();
    }

    //public static AbstractDao<T, PK> getInstance();

    public void dispose() {
        session.close();
        session = null;
    }

    public final void save(T entity) throws IllegalArgumentException, IllegalStateException, PersistSaveException {
        if (session == null) throw new IllegalStateException("No Session");
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
        if (session == null) throw new IllegalStateException("No Session");
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
}
