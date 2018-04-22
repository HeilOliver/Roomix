package at.fhv.roomix.controller.reservation.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ReservationOptionPojo
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationOptionPojo {

    private int id;
    private LocalDate dueDate;

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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
