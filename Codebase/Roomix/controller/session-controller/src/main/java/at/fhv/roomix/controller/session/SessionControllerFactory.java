package at.fhv.roomix.controller.session;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionControllerFactory
 * 22.03.2018 sge
 * <p>
 * The Factory for the SessionController
 */
public class SessionControllerFactory {

    private static final Object lock = new Object();
    private static ISessionController instance;
    private static IInjectDependency injectDependency;

    private SessionControllerFactory() {
    }

    public static ISessionController getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new SessionController();
            }
        }
        return instance;
    }

    static void InjectDependency(IInjectDependency injectInstance) {
        injectDependency = injectInstance;
    }

    /**
     * An interface for injecting dependency
     */
    interface IInjectDependency {
        ISessionController injectDependency();
    }
}
