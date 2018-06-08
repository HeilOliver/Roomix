package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.dataaccess.ISessionController;
import at.fhv.roomix.persist.exception.PersistStateException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * HibernateSessionController
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class HibernateSessionController implements ISessionController {
    private static SessionFactory ourSessionFactory;
    private static boolean initTry = false;
    private static Logger logger = Logger.getLogger(HibernateSessionController.class);
    private static Session currSession;
    private Transaction currTransaction;

    private static void init() {
        initTry = true;
        try {
            Configuration configuration = new Configuration();
            //TODO: remove path to hibernate_h2 (only for deployment)
            configuration.configure("/hibernate_h2.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            logger.fatal(ex.getMessage());
        }
    }

    private static void init(String configFile){
        initTry = true;
        try {
            Configuration configuration = new Configuration();
            configuration.configure(configFile);

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

    public static void start() {
        init();
    }

    public static void startWithConfigFile(String configFile){
        init(configFile);
    }

    public static void disposeHibernate() {
        if (initTry && ourSessionFactory != null) {
            ourSessionFactory.close();
        }
    }

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
        currSession.close();
        currSession = null;
    }

    @Override
    public void rollBackTransaction() {
        if (currTransaction == null)
            throw new IllegalStateException("No transaction open");
        currTransaction.rollback();
        currTransaction = null;
    }

    @Override
    public void closeSession() {
        if (currTransaction != null)
            commitTransaction();
        if (currSession == null)
            return;
        currSession.close();
        currSession = null;
    }

}
