package at.fhv.roomix.controller.reservation.model;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ZimmerPojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

public class ZimmerPojo {
    private int id;
    private OccupationPojo status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OccupationPojo getStatus() {
        return status;
    }

    public void setStatus(OccupationPojo status) {
        this.status = status;
    }


}
