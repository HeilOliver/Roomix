package at.fhv.roomix.controller.reservation.model;

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

    private LocalDate dueDate;
    private String description;
    private int id;
    private int price;
    private int amount;

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

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
