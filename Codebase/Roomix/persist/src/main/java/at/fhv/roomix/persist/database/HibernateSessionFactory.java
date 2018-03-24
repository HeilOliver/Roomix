package at.fhv.roomix.persist.database;

import at.fhv.roomix.persist.exeption.PersistInternalException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

class HibernateSessionFactory {
    private static org.hibernate.SessionFactory ourSessionFactory;

    private HibernateSessionFactory() {
    }

    static void init() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            Logger logger = Logger.getLogger(HibernateSessionFactory.class);
            logger.fatal(ex.getMessage());
        }
    }

    static Session getSession() throws PersistInternalException {
        if (ourSessionFactory == null)
            throw new PersistInternalException(new IllegalStateException("No SessionFactory"));
        try {
            return ourSessionFactory.openSession();
        } catch (HibernateException e) {
            throw new PersistInternalException(e);
        }
    }
}
