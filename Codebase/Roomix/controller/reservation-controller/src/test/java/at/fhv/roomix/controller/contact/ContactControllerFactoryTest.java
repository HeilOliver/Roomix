package at.fhv.roomix.controller.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ContactControllerFactoryTest
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class ContactControllerFactoryTest {
    @BeforeEach
    void clear() {
        ContactControllerFactory.InjectDependency(null);
    }

    @Test
    void injectInFactory_Null() {
        try {
            IContactController instance0 = ContactControllerFactory.getInstance();
            ContactControllerFactory.InjectDependency(null);
            IContactController instance1 = ContactControllerFactory.getInstance();

            assertEquals(instance0, instance1);
        } catch (Exception e) {
            fail("Exception is thrown");
        }
    }

    @Test
    void getInstance_Get() {
        IContactController instance = ContactControllerFactory.getInstance();
        assertNotNull(instance);
    }

    @Test
    void getInstance_Type() {
        IContactController instance;
        // Without Injection
        instance = ContactControllerFactory.getInstance();
        assertTrue(instance instanceof ContactController);

        // With Injection
        IContactController mock = new ContactMock();
        ContactControllerFactory.InjectDependency(() -> mock);
        instance = ContactControllerFactory.getInstance();
        assertTrue(instance instanceof ContactMock);

        // Without Injection
        ContactControllerFactory.InjectDependency(null);
        instance = ContactControllerFactory.getInstance();
        assertTrue(instance instanceof ContactController);
    }

    @Test
    void injectInFactory_Ok() {
        IContactController instance;

        IContactController mock = new ContactMock();
        ContactControllerFactory.InjectDependency(() -> mock);

        instance = ContactControllerFactory.getInstance();

        assertEquals(mock, instance);
    }
}