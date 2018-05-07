package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.ContactNoteDao;
import at.fhv.roomix.persist.models.ContactNoteEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * ContactNoteFactory
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactNoteFactory extends EntityFactory<ContactNoteEntity, Integer> {
    private static final Object lock = new Object();
    private static ContactNoteFactory instance;

    private ContactNoteFactory() {
        super(ContactNoteDao::new, 20);
    }

    public static ContactNoteFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ContactNoteFactory();
            }
        }
        return instance;
    }
}
