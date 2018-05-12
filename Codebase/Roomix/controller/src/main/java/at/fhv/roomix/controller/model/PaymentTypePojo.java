package at.fhv.roomix.controller.model;

import org.hibernate.validator.constraints.Range;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.contact
 * PaymentTypePojo
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PaymentTypePojo {
    @Range(min = 1) // Set here to 1 because we wont create
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
