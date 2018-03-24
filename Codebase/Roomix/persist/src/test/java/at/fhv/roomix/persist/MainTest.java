package at.fhv.roomix.persist;

import at.fhv.roomix.persist.exeption.PersistInternalException;
import at.fhv.roomix.persist.database.HibernateSessionFactory;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

/**
 * Roomix
 * at.fhv.roomix.persist
 * MainTest
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class MainTest {


    @Test
    void firstTest() throws PersistInternalException {

        final Session session = HibernateSessionFactory.getSession();
        System.out.println("Start");
        try {

        } finally {
            session.close();
        }
        System.out.println("Stop");
    }
}