package at.fhv.roomix.controller.implement.reservationcontroller;

import at.fhv.roomix.controller.implement.adapter.ReservationControllerAdapter;
import at.fhv.roomix.controller.implement.roomassignmentcontroller.IRoomAssignmentController;

public class ControllerFactory {

    private static final Object lock = new Object();
    private static ControllerFactory instance;

    private ControllerFactory() { }

    public static ControllerFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ControllerFactory();
            }
        }
        return instance;
    }

    private IReservationController reservationController = new ReservationController(new ReservationControllerAdapter());

    public IReservationController getReservationController() {
        return reservationController;
    }

    public IRoomAssignmentController getRoomAssignmentController() {
        return null;
    }
}
