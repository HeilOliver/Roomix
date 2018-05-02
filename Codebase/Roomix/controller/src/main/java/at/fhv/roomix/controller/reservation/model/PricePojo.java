package at.fhv.roomix.controller.reservation.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.contact
 * CommentPojo
 * 21/04/2018 Robert S.
 * <p>
 * Enter Description here
 */
public class PricePojo {
    @Range(min = 1)
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
