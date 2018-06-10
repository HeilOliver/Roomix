package at.fhv.roomix.controller.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.implement.session
 * SessionControllerFactoryTest
 * 22.03.2018 sge
 */
class SessionControllerFactoryTest {

    @BeforeEach
    void clear() {
        SessionControllerFactory.InjectDependency(null);
    }

    @Test
    void injectInFactory_Null() {
        try {
            ISessionController instance0 = SessionControllerFactory.getInstance();
            SessionControllerFactory.InjectDependency(null);
            ISessionController instance1 = SessionControllerFactory.getInstance();

            assertEquals(instance0, instance1);
        } catch (Exception e) {
            fail("Exception is thrown");
        }
    }

    @Test
    void getInstance_Get() {
        ISessionController instance = SessionControllerFactory.getInstance();
        assertNotNull(instance);
    }

    @Test
    void getInstance_Type() {
        ISessionController instance;
        // Without Injection
        instance = SessionControllerFactory.getInstance();
        assertTrue(instance instanceof SessionController);

        // With Injection
        ISessionController mock = new SessionControllerMock();
        SessionControllerFactory.InjectDependency(() -> mock);
        instance = SessionControllerFactory.getInstance();
        assertTrue(instance instanceof SessionControllerMock);

        // Without Injection
        SessionControllerFactory.InjectDependency(null);
        instance = SessionControllerFactory.getInstance();
        assertTrue(instance instanceof SessionController);
    }

    @Test
    void injectInFactory_Ok() {
        ISessionController instance;

        ISessionController mock = new SessionControllerMock();
        SessionControllerFactory.InjectDependency(() -> mock);

        instance = SessionControllerFactory.getInstance();

        assertEquals(mock, instance);
    }

}