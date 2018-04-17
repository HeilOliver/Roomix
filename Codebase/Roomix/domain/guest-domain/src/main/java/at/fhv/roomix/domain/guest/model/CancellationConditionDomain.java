package at.fhv.roomix.domain.guest.model;

public class CancellationConditionDomain {

    private int cancellationConditionId;
    private int cancellationFee;
    private int daysBeforeArrival;

    public int getCancellationConditionId() {
        return cancellationConditionId;
    }

    public void setCancellationConditionId(int cancellationConditionId) {
        this.cancellationConditionId = cancellationConditionId;
    }

    public int getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(int cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public int getDaysBeforeArrival() {
        return daysBeforeArrival;
    }

    public void setDaysBeforeArrival(int daysBeforeArrival) {
        this.daysBeforeArrival = daysBeforeArrival;
    }
}
