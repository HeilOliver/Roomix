package at.fhv.roomix.controller.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.contact
 * ArrangementPojo
 * 20/04/2018 Oliver Heil
 * <p>
 * Enter Description here
 */

public class ArrangementPojo {
    @Range(min = 1) // Set here to 1 because we wont create
    private int id;
    private String description;
    private PricePojo price;

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

    public PricePojo getPrice() {
        return price;
    }

    public void setPrice(PricePojo price) {
        this.price = price;
    }
}
