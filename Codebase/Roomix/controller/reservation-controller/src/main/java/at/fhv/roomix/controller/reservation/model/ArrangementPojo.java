package at.fhv.roomix.controller.reservation.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ArrangementPojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

public class ArrangementPojo {

    private int id;
    @NotNull(message = "discount cannot be null")
    private DiscountPojo discount;
    @NotNull(message = "name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    @NotNull(message = "description cannot be null")
    @Size(min = 1, max = 200, message = "Description must be between 1 and 200 characters")
    private String description;
    @NotNull(message = "price cannot be null")
    private PricePojo price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DiscountPojo getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountPojo discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
