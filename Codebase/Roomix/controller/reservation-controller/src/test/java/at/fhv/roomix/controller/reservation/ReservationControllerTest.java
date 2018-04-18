package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.GuestDomainBuilder;
import org.junit.jupiter.api.BeforeAll;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ContactControllerTest
 * 08.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ReservationControllerTest {
    private static GuestDomainBuilderMock mock;

    @BeforeAll
    static void init() {
        mock = new GuestDomainBuilderMock();

        GuestDomainBuilder.injectDependency(() -> mock);
        SessionDomainMock sessionDomain = new SessionDomainMock();
        SessionFactory.inject(sessionDomain);
    }

}