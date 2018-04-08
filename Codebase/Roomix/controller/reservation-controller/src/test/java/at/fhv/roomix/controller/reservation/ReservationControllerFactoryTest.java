package at.fhv.roomix.controller.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationControllerFactoryTest
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class ReservationControllerFactoryTest {
    @BeforeEach
    void clear() {
        ReservationControllerFactory.InjectDependency(null);
    }

    @Test
    void injectInFactory_Null() {
        try {
            IReservationController instance0 = ReservationControllerFactory.getInstance();
            ReservationControllerFactory.InjectDependency(null);
            IReservationController instance1 = ReservationControllerFactory.getInstance();

            assertEquals(instance0, instance1);
        } catch (Exception e) {
            fail("Exception is thrown");
        }
    }

    @Test
    void getInstance_Get() {
        IReservationController instance = ReservationControllerFactory.getInstance();
        assertNotNull(instance);
    }

    @Test
    void getInstance_Type() {
        IReservationController instance;
        // Without Injection
        instance = ReservationControllerFactory.getInstance();
        assertTrue(instance instanceof ReservationController);

        // With Injection
        IReservationController mock = new ReservationMock();
        ReservationControllerFactory.InjectDependency(() -> mock);
        instance = ReservationControllerFactory.getInstance();
        assertTrue(instance instanceof ReservationMock);

        // Without Injection
        ReservationControllerFactory.InjectDependency(null);
        instance = ReservationControllerFactory.getInstance();
        assertTrue(instance instanceof ReservationController);
    }

    @Test
    void injectInFactory_Ok() {
        IReservationController instance;

        IReservationController mock = new ReservationMock();
        ReservationControllerFactory.InjectDependency(() -> mock);

        instance = ReservationControllerFactory.getInstance();

        assertEquals(mock, instance);
    }
}