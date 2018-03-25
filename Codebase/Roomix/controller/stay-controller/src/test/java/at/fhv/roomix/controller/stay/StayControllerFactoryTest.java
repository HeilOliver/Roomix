package at.fhv.roomix.controller.stay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.stay
 * StayControllerFactoryTest
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class StayControllerFactoryTest {
    @BeforeEach
    void clear() {
        StayControllerFactory.InjectDependency(null);
    }

    @Test
    void injectInFactory_Null() {
        try {
            IStayController instance0 = StayControllerFactory.getInstance();
            StayControllerFactory.InjectDependency(null);
            IStayController instance1 = StayControllerFactory.getInstance();

            assertEquals(instance0, instance1);
        } catch (Exception e) {
            fail("Exception is thrown");
        }
    }

    @Test
    void getInstance_Get() {
        IStayController instance = StayControllerFactory.getInstance();
        assertNotNull(instance);
    }

    @Test
    void getInstance_Type() {
        IStayController instance;
        // Without Injection
        instance = StayControllerFactory.getInstance();
        assertTrue(instance instanceof StayController);

        // With Injection
        StayControllerMock mock = new StayControllerMock();
        StayControllerFactory.InjectDependency(() -> mock);
        instance = StayControllerFactory.getInstance();
        assertTrue(instance instanceof StayControllerMock);

        // Without Injection
        StayControllerFactory.InjectDependency(null);
        instance = StayControllerFactory.getInstance();
        assertTrue(instance instanceof StayController);
    }

    @Test
    void injectInFactory_Ok() {
        IStayController instance;

        StayControllerMock mock = new StayControllerMock();
        StayControllerFactory.InjectDependency(() -> mock);

        instance = StayControllerFactory.getInstance();

        assertEquals(mock, instance);
    }
}