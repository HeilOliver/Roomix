package at.fhv.roomix.persist.dao;

import at.fhv.roomix.persist.model.PersonEntity;
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
    public PersonDao() {
        super(PersonEntity.class);
    }

    @Override
    public void save(Session session, PersonEntity entity) {
        session.beginTransaction();

        session.saveOrUpdate(entity.getContactByContact());
        session.save(entity);

        session.getTransaction().commit();
    }
}
