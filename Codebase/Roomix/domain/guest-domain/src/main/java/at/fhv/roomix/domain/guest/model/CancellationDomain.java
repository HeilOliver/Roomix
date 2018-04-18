package at.fhv.roomix.domain.guest.model;

import java.sql.Timestamp;

public class CancellationDomain {

    private int cancellationId;
    private int cancellationCondition;
    private Timestamp cancellationDate;
    private String description;
    private CancellationConditionDomain cancellationConditionByCancellationCondition;

    public int getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(int cancellationId) {
        this.cancellationId = cancellationId;
    }

    public int getCancellationCondition() {
        return cancellationCondition;
    }

    public void setCancellationCondition(int cancellationCondition) {
        this.cancellationCondition = cancellationCondition;
    }

    public Timestamp getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Timestamp cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CancellationConditionDomain getCancellationConditionByCancellationCondition() {
        return cancellationConditionByCancellationCondition;
    }

    public void setCancellationConditionByCancellationCondition(CancellationConditionDomain cancellationConditionByCancellationCondition) {
        this.cancellationConditionByCancellationCondition = cancellationConditionByCancellationCondition;
    }
}
