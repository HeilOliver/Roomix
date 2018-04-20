package at.fhv.roomix.controller.reservation.model;



public class ZimmerPojo {
    private int id;

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

    private OccupationPojo status;




}
