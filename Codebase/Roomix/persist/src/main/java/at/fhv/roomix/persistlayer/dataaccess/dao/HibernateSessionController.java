package at.fhv.roomix.persistlayer.dataaccess.dao;

import at.fhv.roomix.persist.HibernateSessionFactory;
import at.fhv.roomix.persistlayer.dataaccess.ISessionController;
import at.fhv.roomix.persistlayer.exception.PersistStateException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess
 * HibernateSessionController
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class HibernateSessionController implements ISessionController {
    private static SessionFactory ourSessionFactory;
    private static boolean initTry = false;
    private static Logger logger = Logger.getLogger(HibernateSessionFactory.class);
    private static Session currSession;

    private static void init() {
        initTry = true;
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            logger.fatal(ex.getMessage());
        }
    }

    public static Session getSession() throws PersistStateException {
        if (!initTry) init();
        if (ourSessionFactory == null) return null;
        try {
            if (currSession == null)
                currSession = ourSessionFactory.openSession();
            return currSession;
        } catch (HibernateException e) {
            logger.fatal(e.getMessage());
            throw new PersistStateException("No Session can be created, " +
                    "see inner exception for details", e);
        }
    }

    public static void disposeHibernate() {
        if (initTry && ourSessionFactory != null) {
            ourSessionFactory.close();
        }
    }

    private Transaction currTransaction;

    @Override
    public void startTransaction() throws PersistStateException {
        if (currTransaction != null)
            throw new IllegalStateException("Transaction already started");
        currTransaction = getSession().beginTransaction();
    }

    @Override
    public void commitTransaction() {
        if (currTransaction == null)
            throw new IllegalStateException("No transaction open");
        currTransaction.commit();
        currTransaction = null;
    }

    @Override
    public void rolBackTransaction() {
        if (currTransaction == null)
            throw new IllegalStateException("No transaction open");
        currTransaction.rollback();
        currTransaction = null;
    }

    @Override
    public void closeSession() {
        if (currTransaction != null)
            commitTransaction();
        currSession.close();
    }

}
