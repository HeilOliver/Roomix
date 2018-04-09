package at.fhv.roomix.persist;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.persist
 * AbstractDaoTest
 * 04/04/2018 Oliver
 * <p>
 * Enter Description here
 */
class AbstractDaoTest {

    @Test
    void validSession() {
        AbstractDaoMock mock1 = AbstractDaoMock.getInstance();
        AbstractDaoMock mock2 = AbstractDaoMock.getInstance();

        Session session = mock1.getSession();
        assertNotNull(session);
        assertTrue(session.isOpen());

        assertNotEquals(session, mock2.getSession());
    }

    @Test
    void insertNull() {
        AbstractDaoMock mock = AbstractDaoMock.getInstance();
        assertThrows(IllegalArgumentException.class, () -> mock.save(null));
        assertThrows(IllegalArgumentException.class, () -> mock.load(null));
    }

    @Test
    void insertWithInvalidSession() {
        AbstractDaoMock mock = AbstractDaoMock.getInstance();
        mock.dispose();

        assertThrows(IllegalStateException.class, () -> mock.save(new Object()));
        assertThrows(IllegalStateException.class, () -> mock.load(1));
        assertThrows(IllegalStateException.class, mock::loadAll);
    }

}