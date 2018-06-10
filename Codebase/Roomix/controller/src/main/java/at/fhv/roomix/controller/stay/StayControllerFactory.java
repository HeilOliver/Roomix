package at.fhv.roomix.controller.stay;


/**
 * Roomix
 * at.fhv.roomix.implement.session
 * StayControllerFactory
 * 22.03.2018 OliverH
 * <p>
 * The Factory for the StayController
 */
public class StayControllerFactory {

    private static final Object lock = new Object();
    private static IStayController instance;
    private static IInjectDependency injectDependency;

    private StayControllerFactory() {
    }

    public static IStayController getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new StayController();
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
        IStayController injectDependency();
    }
}
