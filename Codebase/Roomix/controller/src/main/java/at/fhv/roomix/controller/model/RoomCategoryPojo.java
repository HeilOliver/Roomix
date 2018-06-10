package at.fhv.roomix.controller.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Roomix
 * at.fhv.roomix.implement.reservation.contact
 * RoomCategoryPojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

public class RoomCategoryPojo {
    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
