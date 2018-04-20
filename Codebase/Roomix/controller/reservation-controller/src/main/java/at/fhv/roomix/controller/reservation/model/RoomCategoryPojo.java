package at.fhv.roomix.controller.reservation.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * RoomCategoryPojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

public class RoomCategoryPojo {
    private int id;
    private String discription;
    private Collection<ZimmerPojo> rooms;

    public RoomCategoryPojo(){
        rooms = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Collection<ZimmerPojo> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<ZimmerPojo> rooms) {
        this.rooms = rooms;
    }
}
