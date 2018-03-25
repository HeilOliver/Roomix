package at.fhv.roomix.persist.database;

import at.fhv.roomix.persist.exeption.PersistInternalException;
import at.fhv.roomix.persist.exeption.PersistLoadException;
import at.fhv.roomix.persist.exeption.PersistSaveException;
import at.fhv.roomix.persist.model.PersonEntity;

import java.util.Collection;
import java.util.List;

/**
 * Roomix
 * at.fhv.roomix.persist.database
 * IDataBase
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public interface IDataBase {

    Collection<PersonEntity> getPersonByName(String name) throws PersistInternalException, PersistLoadException;

    void savePerson(PersonEntity entity)throws PersistInternalException, PersistSaveException;

    Collection<PersonEntity> getAllPersons() throws PersistInternalException, PersistLoadException;

}
