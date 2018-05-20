package at.fhv.roomix.controller.implement.roomassignmentcontroller;

import at.fhv.roomix.controller.implement.adapter.RoomAssignmentControllerAdapter;

public class RoomAssignmentFactory {

    static {
        callback = new RoomAssignmentControllerAdapter();
    }

    private static IRoomAssignmentCallback callback;
    private static final Object lock = new Object();
    private static RoomAssignmentController instance;

    public static RoomAssignmentController getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new RoomAssignmentController(callback);
            }
        }
        return instance;
    }
}
