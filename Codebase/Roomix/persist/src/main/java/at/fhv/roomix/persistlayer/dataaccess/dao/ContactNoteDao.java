package at.fhv.roomix.persistlayer.dataaccess.dao;

import at.fhv.roomix.persist.model.ContactNoteEntity;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess.dao
 * ContactNoteDao
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactNoteDao extends AbstractDao<ContactNoteEntity, Integer> {

    public ContactNoteDao() {
        super(ContactNoteEntity.class);
    }

}
