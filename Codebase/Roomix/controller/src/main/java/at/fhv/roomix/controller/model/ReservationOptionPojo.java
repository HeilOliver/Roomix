package at.fhv.roomix.controller.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.contact
 * ReservationOptionPojo
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationOptionPojo {

    private int optionId;
    private LocalDate optionDueDate;

    @NotNull(message = "optionDescription cannot be null")
    @Size(min = 1, max = 200, message = "Description must be between 1 and 200 characters")
    private String optionDescription;
    @NotNull(message = "optionFee cannot be null")
    private PricePojo optionFee;

    private byte optionStatus;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public LocalDate getOptionDueDate() {
        return optionDueDate;
    }

    public void setOptionDueDate(LocalDate optionDueDate) {
        this.optionDueDate = optionDueDate;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public PricePojo getOptionFee() {
        return optionFee;
    }

    public void setOptionFee(PricePojo optionFee) {
        this.optionFee = optionFee;
    }

    public byte getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(byte optionStatus) {
        this.optionStatus = optionStatus;
    }
}
