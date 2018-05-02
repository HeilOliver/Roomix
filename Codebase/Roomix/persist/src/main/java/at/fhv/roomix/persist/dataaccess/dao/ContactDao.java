package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.ContactEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * ContactDao
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactDao extends AbstractDao<ContactEntity, Integer> {

    public ContactDao() {
        super(ContactEntity.class);
    }

}
