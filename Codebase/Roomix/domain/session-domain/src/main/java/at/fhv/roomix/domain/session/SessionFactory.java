package at.fhv.roomix.domain.session;

/**
 * Roomix
 * at.fhv.roomix.domain.guest
 * SessionFactory
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SessionFactory {

    private static final Object lock = new Object();
    private static ISessionDomain instance;

    private SessionFactory() {

    }

    public static ISessionDomain getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new SessionDomain();
            }
        }
        return instance;
    }


}
