package at.fhv.roomix.controller.reservation.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * CategoryPojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

public class CategoryPojo {
    private int id;
    private String discription;
    private Collection<ZimmerPojo> rooms;

    public CategoryPojo(){
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
