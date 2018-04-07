package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.PersonEntity;
import org.hibernate.HibernateException;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * PersonDao
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class PersonDao extends AbstractDao<PersonEntity, Integer> {

    private PersonDao(){
        super(PersonEntity.class);
    }

    public PersonDao getInstance() {
        return new PersonDao();
    }

    @Override
    protected void internalSave(PersonEntity entity) throws HibernateException {
        session.beginTransaction();
        session.saveOrUpdate(entity.getContactByContact());
        session.save(entity);
        session.getTransaction().commit();
    }
}
