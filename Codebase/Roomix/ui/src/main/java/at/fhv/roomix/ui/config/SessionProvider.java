package at.fhv.roomix.ui.config;

/**
 * Roomix
 * at.fhv.roomix.ui.config
 * SessionProvider
 * 08/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class SessionProvider {

    private static final Object lock = new Object();
    private static SessionProvider instance;
    private static long sessionId;

    private SessionProvider() {

    }

    public static SessionProvider getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new SessionProvider();
            }
        }
        return instance;
    }

    public static long getSessionId() {
        return sessionId;
    }

    public static void setSessionId(long sessionId) {
        SessionProvider.sessionId = sessionId;
    }
}
