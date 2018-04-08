package at.fhv.roomix.domain.session;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.domain.session
 * SessionDomainTest
 * 05/04/2018 Oliver
 * <p>
 * Enter Description here
 */
class SessionFactoryTest {

    @Test
    void getSessionFactory() {
        ISessionDomain instance1 = SessionFactory.getInstance();
        assertNotNull(instance1);
        ISessionDomain instance2 = SessionFactory.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    void injectSessionDomain() {
        SessionDomainMock mock = new SessionDomainMock();
        ISessionDomain instance;

        SessionFactory.inject(mock);
        instance = SessionFactory.getInstance();

        assertEquals(mock, instance);

        SessionFactory.inject(null);
        instance = SessionFactory.getInstance();
        assertNotEquals(mock, instance);
    }
}