package at.fhv.roomix.controller.contact;

/**
 * Roomix
 * at.fhv.roomix.controller.contact
 * SessionControllerFactory
 * 18.04.2018 sge
 * <p>
 * The Factory for the ContactController
 */
public class ContactControllerFactory {

    private static final Object lock = new Object();
    private static IContactController instance;
    private static IInjectDependency injectDependency;

    private ContactControllerFactory() {
    }

    public static IContactController getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new ContactController();
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
        IContactController injectDependency();
    }
}
