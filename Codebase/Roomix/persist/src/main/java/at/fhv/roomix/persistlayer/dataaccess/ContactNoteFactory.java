package at.fhv.roomix.persistlayer.dataaccess;

import at.fhv.roomix.persist.model.ContactNoteEntity;
import at.fhv.roomix.persistlayer.dataaccess.dao.ContactNoteDao;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess
 * ContactNoteFactory
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactNoteFactory extends EntityFactory<ContactNoteEntity, Integer> {
    private static final Object lock = new Object();
    private static ContactNoteFactory instance;

    private ContactNoteFactory() {
        super(ContactNoteDao::new, 2);
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
