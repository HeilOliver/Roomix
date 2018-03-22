package at.fhv.roomix.domain.guest;

import org.hibernate.annotations.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.domain.guest
 * GuestDomainFactoryTest
 * 22/03/2018 Oliver
 * <p>
 * Enter Description here
 */
class GuestDomainFactoryTest {

    @Test
    void injectInFactory_null() {
        GuestDomainMock guestDomainMock = new GuestDomainMock();

        IGuestDomain guestDomain = GuestDomainFactory.GetInstance();
        assertNotEquals(guestDomain, guestDomainMock);

        GuestDomainFactory.InjectDependency(null);
        IGuestDomain newGuestDomain = GuestDomainFactory.GetInstance();
        assertNotEquals(guestDomain, newGuestDomain);
        assertNotEquals(guestDomainMock, newGuestDomain);
    }

    @Test
    void getInstance_get() {
        IGuestDomain guestDomain = GuestDomainFactory.GetInstance();
        assertNotNull(guestDomain);
    }

    @Test
    void getInstance_type() {
        IGuestDomain guestDomain = GuestDomainFactory.GetInstance();
        assertTrue(guestDomain instanceof GuestDomain);
    }

    @Test
    void injectInFactory_ok() {
        GuestDomainMock guestDomainMock = new GuestDomainMock();
        GuestDomainFactory.InjectDependency(guestDomainMock);

        IGuestDomain iGuestDomain = GuestDomainFactory.GetInstance();

        assertEquals(iGuestDomain, guestDomainMock);
    }



}