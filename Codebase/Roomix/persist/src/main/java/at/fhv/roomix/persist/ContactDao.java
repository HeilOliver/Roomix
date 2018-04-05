package at.fhv.roomix.persist;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * ContactDao
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactDao extends AbstractDao<ContactEntity, Integer> {
    private ContactDao() {
        super(ContactEntity.class);
    }


    public static ContactDao getInstance() {
        return new ContactDao();
    }

}
