package at.fhv.roomix.controller.reservation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
