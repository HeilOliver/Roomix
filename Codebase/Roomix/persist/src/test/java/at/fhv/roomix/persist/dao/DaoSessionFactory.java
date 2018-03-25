package at.fhv.roomix.persist.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * DaoSessionFactory
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class DaoSessionFactory {

    private static org.hibernate.SessionFactory ourSessionFactory;
    private static boolean exceptionIsThrown = false;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ignored) {
            exceptionIsThrown = true;
        }
    }

    static Session getSession() {
        if (ourSessionFactory == null) return null;
        try {
            return ourSessionFactory.openSession();
        } catch (HibernateException ignored) {
            exceptionIsThrown = true;
        }
        return null;
    }

    @Test
    void getSession_isValid() {
        assertFalse(exceptionIsThrown, "Exception is already happen");
        Session session = getSession();
        assertNotNull(session, "Session is Null");
    }
}
