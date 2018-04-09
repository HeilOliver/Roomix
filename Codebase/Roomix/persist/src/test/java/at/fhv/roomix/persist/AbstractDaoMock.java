package at.fhv.roomix.persist;

import org.hibernate.Session;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * AbstractDaoMock
 * 04/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class AbstractDaoMock extends AbstractDao<Object, Integer> {

    private AbstractDaoMock() {
        super(Object.class);
    }

    public static AbstractDaoMock getInstance() {
        return new AbstractDaoMock();
    }

    public Session getSession() {
        return session;
    }
}
