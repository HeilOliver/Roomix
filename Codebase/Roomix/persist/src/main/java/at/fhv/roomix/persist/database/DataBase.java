package at.fhv.roomix.persist.database;

import at.fhv.roomix.persist.dao.ContactDao;
import at.fhv.roomix.persist.dao.PersonDao;
import at.fhv.roomix.persist.exeption.PersistInternalException;
import at.fhv.roomix.persist.exeption.PersistLoadException;
import at.fhv.roomix.persist.exeption.PersistSaveException;
import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persist.model.PersonEntity;
import org.hibernate.Session;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.persist.database
 * DataBase
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class DataBase implements IDataBase {

    private Session getSession() throws PersistInternalException {
        return HibernateSessionFactory.getSession();
    }

    @Override
    public Collection<PersonEntity> getPersonByName(String name) throws PersistInternalException, PersistLoadException {
        Session session = getSession();
        PersonDao personDao = PersonDao.getInstance();
        session.close();
        List<PersonEntity> personEntities = personDao.loadAll(session);

        return personEntities.stream()
                .filter(p -> p.getContactByContact().getForename() != null)
                .filter(p -> p.getContactByContact().getForename().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void savePerson(PersonEntity entity) throws PersistInternalException, PersistSaveException {
        Session session = getSession();
        PersonDao.getInstance().save(session, entity);
        session.close();
    }

    @Override
    public Collection<PersonEntity> getAllPersons() throws PersistInternalException, PersistLoadException {
        Session session = getSession();
        PersonDao personDao = PersonDao.getInstance();
        List<PersonEntity> personEntities = personDao.loadAll(session);
        session.close();
        return personEntities;
    }
}
