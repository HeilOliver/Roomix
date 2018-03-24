package at.fhv.roomix.persist.database;

/**
 * Roomix
 * at.fhv.roomix.persist.database
 * PersistFactory
 * 24/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class PersistFactory {

    private static IDataBase instance;
    private static IInjectDependency injectDependency;
    private static final Object lock = new Object();

    private PersistFactory() {
    }

    public IDataBase getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                HibernateSessionFactory.init();
                instance = new DataBase();
            }
        }
        return instance;
    }

    /**
     * An interface for injecting dependency
     */
    interface IInjectDependency {
        IDataBase injectDependency();
    }
}
