package at.fhv.roomix.persist.dao;

import at.fhv.roomix.persist.model.ContactEntity;
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

    public void save(Session session, T entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public T load(Session session, PK id) {
        return session.get(type, id);
    }

    public List<T> loadAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);

        Root<T> from = query.from(type);
        CriteriaQuery<T> select = query.select(from);

        return session.createQuery(select).getResultList();
    }

}
