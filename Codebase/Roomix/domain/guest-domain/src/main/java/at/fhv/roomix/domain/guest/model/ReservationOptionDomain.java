package at.fhv.roomix.domain.guest.model;

import java.sql.Date;

public class ReservationOptionDomain {
    private int optionId;
    private int daysBeforeArrival;
    private String optionDescription;
    private byte optionStatus;
    private int optionPercentage;


    public int getDaysBeforeArrival() {
        return daysBeforeArrival;
    }

    public void setDaysBeforeArrival(int daysBeforeArrival) {
        this.daysBeforeArrival = daysBeforeArrival;
    }

    public int getOptionPercentage() {
        return optionPercentage;
    }

    public void setOptionPercentage(int optionFee) {
        this.optionPercentage = optionFee;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public byte getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(byte optionStatus) {
        this.optionStatus = optionStatus;
    }
}
