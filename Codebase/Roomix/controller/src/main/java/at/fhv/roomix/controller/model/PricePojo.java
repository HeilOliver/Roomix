package at.fhv.roomix.controller.model;

import org.hibernate.validator.constraints.Range;

/**
 * Roomix
 * at.fhv.roomix.implement.reservation.contact
 * CommentPojo
 * 21/04/2018 Robert S.
 * <p>
 * Enter Description here
 */
public class PricePojo {
    @Range(min = 1)
    private int price;

    public PricePojo(int price) {
        this.price = price;
    }

    public PricePojo() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
