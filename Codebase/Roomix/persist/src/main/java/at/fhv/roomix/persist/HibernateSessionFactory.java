package at.fhv.roomix.persist;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class HibernateSessionFactory {
    private static SessionFactory ourSessionFactory;
    private static boolean initTry = false;
    private static Logger logger = Logger.getLogger(HibernateSessionFactory.class);


    private HibernateSessionFactory() {
    }

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

    static Session getSession() throws IllegalStateException {
        if (!initTry) init();
        if (ourSessionFactory == null) return null;
        try {
            return ourSessionFactory.openSession();
        } catch (HibernateException e) {
            logger.fatal(e.getMessage());
            return null;
        }
    }
}
