package at.fhv.roomix.controller.invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.invoice
 * InvoiceControllerFactoryTest
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class InvoiceControllerFactoryTest {
    @BeforeEach
    void clear() {
        InvoiceControllerFactory.InjectDependency(null);
    }

    @Test
    void injectInFactory_Null() {
        try {
            IInvoiceController instance0 = InvoiceControllerFactory.getInstance();
            InvoiceControllerFactory.InjectDependency(null);
            IInvoiceController instance1 = InvoiceControllerFactory.getInstance();

            assertEquals(instance0, instance1);
        } catch (Exception e) {
            fail("Exception is thrown");
        }
    }

    @Test
    void getInstance_Get() {
        IInvoiceController instance = InvoiceControllerFactory.getInstance();
        assertNotNull(instance);
    }

    @Test
    void getInstance_Type() {
        IInvoiceController instance;
        // Without Injection
        instance = InvoiceControllerFactory.getInstance();
        assertTrue(instance instanceof InvoiceController);

        // With Injection
        IInvoiceController mock = new InvoiceControllerMock();
        InvoiceControllerFactory.InjectDependency(() -> mock);
        instance = InvoiceControllerFactory.getInstance();
        assertTrue(instance instanceof InvoiceControllerMock);

        // Without Injection
        InvoiceControllerFactory.InjectDependency(null);
        instance = InvoiceControllerFactory.getInstance();
        assertTrue(instance instanceof InvoiceController);
    }

    @Test
    void injectInFactory_Ok() {
        IInvoiceController instance;

        IInvoiceController mock = new InvoiceControllerMock();
        InvoiceControllerFactory.InjectDependency(() -> mock);

        instance = InvoiceControllerFactory.getInstance();

        assertEquals(mock, instance);
    }
}