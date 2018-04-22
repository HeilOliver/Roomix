package at.fhv.roomix.controller.reservation.model;

import java.util.Collection;
import java.util.HashSet;

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
    private DiscountPojo discount;
    private String name;
    private String description;
    private int price;



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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
