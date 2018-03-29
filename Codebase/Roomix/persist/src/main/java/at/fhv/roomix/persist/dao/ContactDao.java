package at.fhv.roomix.persist.dao;

import at.fhv.roomix.persist.model.ContactEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * ContactDao
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactDao extends AbstractDao<ContactEntity, Integer> {
    public ContactDao() {
        super(ContactEntity.class);
    }
}
