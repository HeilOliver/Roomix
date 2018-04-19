package at.fhv.roomix.domain.guest.model;

import java.sql.Date;

public class ReservationOptionDomain {
    private int optionId;
    private Date optionDueDate;
    private String optionDescription;
    private byte optionStatus;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public Date getOptionDueDate() {
        return optionDueDate;
    }

    public void setOptionDueDate(Date optionDueDate) {
        this.optionDueDate = optionDueDate;
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
