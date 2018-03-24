package at.fhv.roomix.persist.database;

import at.fhv.roomix.persist.model.PersonEntity;

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

    List<PersonEntity> getPersonByName(String name);

    void savePerson(PersonEntity entity);

    List<PersonEntity> getAllPersons();

}
