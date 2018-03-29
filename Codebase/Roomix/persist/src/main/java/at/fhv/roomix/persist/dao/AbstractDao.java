package at.fhv.roomix.persist.dao;

import at.fhv.roomix.persist.exeption.PersistInternalException;
import at.fhv.roomix.persist.exeption.PersistLoadException;
import at.fhv.roomix.persist.exeption.PersistSaveException;
import at.fhv.roomix.persist.model.ContactEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * AbstractDao
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
abstract class AbstractDao<T, PK extends Serializable> {
    private Class<T> type;

    AbstractDao(Class<T> type) {
        this.type = type;
    }

    public void save(Session session, T entity) throws PersistInternalException, PersistSaveException {
        if (session == null) throw new PersistInternalException(new IllegalStateException("No Session"));
        if (entity == null) throw new PersistSaveException("No Entity is provided");

        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new PersistInternalException(e.getCause());
        }
    }

    public T load(Session session, PK id) throws PersistInternalException, PersistLoadException {
        if (session == null) throw new PersistInternalException(new IllegalStateException("No Session"));
        if (id == null) throw new PersistLoadException("No PrimaryKey is provided");

        try {
            return session.get(type, id);
        } catch (HibernateException e) {
            throw new PersistLoadException(e.getCause());
        }
    }

    public List<T> loadAll(Session session) throws PersistInternalException, PersistLoadException {
        if (session == null) throw new PersistInternalException(new IllegalStateException("No Session"));

        try {
            CriteriaQuery<T> select;
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<T> query = builder.createQuery(type);

                Root<T> from = query.from(type);
                select = query.select(from);
            } catch (HibernateException e) {
                throw new PersistInternalException(e.getCause());
            }
            return session.createQuery(select).getResultList();
        } catch (HibernateException e) {
            throw new PersistLoadException(e.getCause());
        }
    }

}
