package at.fhv.roomix.controller.report;

/**
 * Roomix
 * at.fhv.roomix.controller.session
 * SessionControllerFactory
 * 22.03.2018 sge
 *
 * The Factory for the ReportController
 */
public class ReportControllerFactory {

    private static IReportController instance;
    private static IInjectDependency injectDependency;
    private static final Object lock = new Object();

    private ReportControllerFactory() {
    }

    public static IReportController getInstance() {
        if (injectDependency != null)
            return injectDependency.injectDependency();
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null) {
                instance = new ReportController();
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
        IReportController injectDependency();
    }
}
