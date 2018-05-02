package at.fhv.roomix.controller.reservation;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionControllerFactory
 * 22.03.2018 sge
 * <p>
 * The Factory for the ReservationController
 */
public class ReservationControllerFactory {

    private static final Object lock = new Object();
    private static IReservationController instance;
    private static IInjectDependency injectDependency;

    private ReservationControllerFactory() {
    }

    public static IReservationController getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationController();
            }
        }
        return instance;
    }

    public static void InjectDependency(IInjectDependency injectInstance) {
        injectDependency = injectInstance;
    }

    /**
     * An interface for injecting dependency
     */
    public interface IInjectDependency {
        IReservationController injectDependency();
    }
}
