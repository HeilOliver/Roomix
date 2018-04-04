package at.fhv.roomix.domain.guest.model;

import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.PersistLoadException;
import at.fhv.roomix.persist.model.ContactEntity;

import java.util.HashMap;

/**
 * Roomix
 * at.fhv.roomix.domain.guest.model
 * GuestFactory
 * 04/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class GuestFactory {

    private GuestFactory() {
    }

    private HashMap<Integer, GuestDomain> instances = new HashMap<>();

    public GuestDomain getInstance(int id) {

        ContactDao dao = ContactDao.getInstance();
        ContactEntity loadedEntity = null;
        try {
            loadedEntity = dao.load(id);
        } catch (PersistLoadException e) {
            e.printStackTrace();
        }
        
        return new GuestDomain(loadedEntity);
    }
}
