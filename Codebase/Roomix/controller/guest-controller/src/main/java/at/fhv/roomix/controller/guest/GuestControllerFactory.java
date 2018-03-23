package at.fhv.roomix.controller.guest;

/**
 * Roomix
 * at.fhv.roomix.controller.guest
 * GuestControllerFactory
 * 22.03.2018 sge
 *
 * The Factory for the GuestController
 */
public class GuestControllerFactory {

    private static IGuestController instance;
    private static final Object lock = new Object();

    private GuestControllerFactory() {

    }

    public static IGuestController GetInstance() {
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new GuestController();
            }
        }
        return instance;
    }

    static void InjectDependency(IGuestController injectInstance) {
        instance = injectInstance;
    }



}
