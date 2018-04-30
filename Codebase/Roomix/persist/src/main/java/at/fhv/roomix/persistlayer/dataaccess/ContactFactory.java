package at.fhv.roomix.persistlayer.dataaccess;

import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persistlayer.dataaccess.dao.ContactDao;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess
 * ContactFactory
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactFactory extends EntityFactory<ContactEntity, Integer> {

    private static final Object lock = new Object();
    private static ContactFactory instance;

        private ContactFactory() {
            super(ContactDao::new, 1);
        }

    public static ContactFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ContactFactory();
            }
        }
        return instance;
    }

    @Override
    public void saveOrUpdate(ContactEntity entity) {
        super.saveOrUpdate(entity);

//        if (entity.getCreditCardsByContactId() != null)
//            entity.getCreditCardsByContactId()
//                    .forEach(CreditCardFactory.getInstance()::saveOrUpdate);
//
//        if (entity.getContactNotesByContactId() != null)
//            entity.getContactNotesByContactId()
//                    .forEach(ContactNoteFactory.getInstance()::saveOrUpdate);
    }
}
