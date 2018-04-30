package at.fhv.roomix.persistlayer.dataaccess.dao;

import at.fhv.roomix.persist.model.ContactEntity;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess.dao
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
