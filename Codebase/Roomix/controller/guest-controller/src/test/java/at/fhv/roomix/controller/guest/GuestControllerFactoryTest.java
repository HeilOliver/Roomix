package at.fhv.roomix.controller.guest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.guest
 * GuestControllerFactoryTest
 * 22.03.2018 sge
 *
 *
 */
class GuestControllerFactoryTest {

    @Test
    void injectInFactory_null() {
        GuestControllerMock guestControllerMock = new GuestControllerMock();

        IGuestController guestController = GuestControllerFactory.GetInstance();
        assertNotEquals(guestController, guestControllerMock);

        GuestControllerFactory.InjectDependency(null);
        IGuestController newGuestController = GuestControllerFactory.GetInstance();
        assertNotEquals(guestController, newGuestController);
        assertNotEquals(guestControllerMock, newGuestController);
    }

    @Test
    void getInstance_get() {
        IGuestController guestController = GuestControllerFactory.GetInstance();
        assertNotNull(guestController);
    }

    @Test
    void getInstance_type() {
        IGuestController guestController = GuestControllerFactory.GetInstance();
        assertTrue(guestController instanceof GuestController);
    }

    @Test
    void injectInFactory_ok() {
        GuestControllerMock guestControllerMock = new GuestControllerMock();
        GuestControllerFactory.InjectDependency(guestControllerMock);

        IGuestController iGuestController = GuestControllerFactory.GetInstance();

        assertEquals(iGuestController, guestControllerMock);
    }


}