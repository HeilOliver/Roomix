package at.fhv.roomix.controller.reservation;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionControllerFactory
 * 22.03.2018 sge
 *
 * The Factory for the ReservationController
 */
public class ReservationControllerFactory {

    private static IReservationController instance;
    private static IInjectDependency injectDependency;
    private static final Object lock = new Object();

    private ReservationControllerFactory() {
    }

    public static IReservationController getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new ReservationMock();
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
