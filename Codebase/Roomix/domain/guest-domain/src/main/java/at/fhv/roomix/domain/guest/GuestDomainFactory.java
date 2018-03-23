package at.fhv.roomix.domain.guest;

/**
 * Roomix
 * at.fhv.roomix.domain.guest
 * GuestDomainFactory
 * 22/03/2018 Oliver
 * <p>
 * The Factory for the GuestDomain
 * */
public class GuestDomainFactory {

    private static IGuestDomain instance;
    private static final Object lock = new Object();

    private GuestDomainFactory() {
    }

    public static IGuestDomain GetInstance() {
        if (instance != null) return instance;

        synchronized (lock) {
            if (instance == null)
                instance = new GuestDomain();
        }
        return instance;
    }

    static void InjectDependency(IGuestDomain injectInstance) {
        instance = injectInstance;
    }
}
