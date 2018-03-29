package at.fhv.roomix.controller.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.report
 * ReportControllerFactoryTest
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class ReportControllerFactoryTest {
    @BeforeEach
    void clear() {
        ReportControllerFactory.InjectDependency(null);
    }

    @Test
    void injectInFactory_Null() {
        try {
            IReportController instance0 = ReportControllerFactory.getInstance();
            ReportControllerFactory.InjectDependency(null);
            IReportController instance1 = ReportControllerFactory.getInstance();

            assertEquals(instance0, instance1);
        } catch (Exception e) {
            fail("Exception is thrown");
        }
    }

    @Test
    void getInstance_Get() {
        IReportController instance = ReportControllerFactory.getInstance();
        assertNotNull(instance);
    }

    @Test
    void getInstance_Type() {
        IReportController instance;
        // Without Injection
        instance = ReportControllerFactory.getInstance();
        assertTrue(instance instanceof ReportController);

        // With Injection
        IReportController mock = new ReportControllerMock();
        ReportControllerFactory.InjectDependency(() -> mock);
        instance = ReportControllerFactory.getInstance();
        assertTrue(instance instanceof ReportControllerMock);

        // Without Injection
        ReportControllerFactory.InjectDependency(null);
        instance = ReportControllerFactory.getInstance();
        assertTrue(instance instanceof ReportController);
    }

    @Test
    void injectInFactory_Ok() {
        IReportController instance;

        IReportController mock = new ReportControllerMock();
        ReportControllerFactory.InjectDependency(() -> mock);

        instance = ReportControllerFactory.getInstance();

        assertEquals(mock, instance);
    }
}