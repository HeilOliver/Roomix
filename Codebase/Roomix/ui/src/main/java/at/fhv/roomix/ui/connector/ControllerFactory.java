package at.fhv.roomix.ui.connector;

import at.fhv.roomix.controller.invoice.IInvoiceController;
import at.fhv.roomix.controller.invoice.InvoiceControllerFactory;
import at.fhv.roomix.controller.report.IReportController;
import at.fhv.roomix.controller.report.ReportControllerFactory;
import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.session.ISessionController;
import at.fhv.roomix.controller.session.SessionControllerFactory;
import at.fhv.roomix.controller.stay.IStayController;
import at.fhv.roomix.controller.stay.StayControllerFactory;

/**
 * Roomix
 * at.fhv.roomix.ui.connector
 * ControllerFactory
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ControllerFactory {
    private ControllerFactory() {
    }

    public static void init() {
    }

    public static IInvoiceController getInvoiceController() {
        return InvoiceControllerFactory.getInstance();
    }

    public static IReservationController getReservationController() {
        return ReservationControllerFactory.getInstance();
    }

    public static IReportController getReportController() {
        return ReportControllerFactory.getInstance();
    }

    public static ISessionController getSessionController() {
        return SessionControllerFactory.getInstance();
    }

    public static IStayController getStayController() {
        return StayControllerFactory.getInstance();
    }
}
