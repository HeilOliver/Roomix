package at.fhv.roomix.controller.invoice;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionControllerFactory
 * 22.03.2018 sge
 *
 * The Factory for the InvoiceController
 */
public class InvoiceControllerFactory {

    private static IInvoiceController instance;
    private static IInjectDependency injectDependency;
    private static final Object lock = new Object();

    private InvoiceControllerFactory() {
    }

    public static IInvoiceController getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new InvoiceController();
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
        IInvoiceController injectDependency();
    }
}
