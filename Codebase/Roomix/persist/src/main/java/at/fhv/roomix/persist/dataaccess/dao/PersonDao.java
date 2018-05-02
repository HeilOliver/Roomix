package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.PersonEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * PersonDao
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersonDao extends AbstractDao<PersonEntity, Integer> {

    public PersonDao() {
        super(PersonEntity.class);
    }

}
