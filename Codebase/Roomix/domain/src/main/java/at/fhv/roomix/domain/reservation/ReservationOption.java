package at.fhv.roomix.domain.reservation;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * ReservationOption
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationOption {
    private int id;
    private int daysBeforeArrival;
    private String description;
    private OptionStatus status;
    private int percentage;

    public ReservationOption() {
        status = OptionStatus.NEW;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDaysBeforeArrival() {
        return daysBeforeArrival;
    }

    public void setDaysBeforeArrival(int daysBeforeArrival) {
        this.daysBeforeArrival = daysBeforeArrival;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OptionStatus getStatus() {
        return status;
    }

    public void setStatus(OptionStatus status) {
        this.status = status;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
