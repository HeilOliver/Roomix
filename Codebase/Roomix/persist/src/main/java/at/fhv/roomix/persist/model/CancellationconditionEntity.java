package at.fhv.roomix.persist.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "cancellationcondition", schema = "roomix")
public class CancellationconditionEntity {
    private int cancellationConditionId;

    @Id
    @javax.persistence.Column(name = "CancellationConditionID")
    public int getCancellationConditionId() {
        return cancellationConditionId;
    }

    public void setCancellationConditionId(int cancellationConditionId) {
        this.cancellationConditionId = cancellationConditionId;
    }

    private int cancellationFee;

    @Basic
    @javax.persistence.Column(name = "CancellationFee")
    public int getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(int cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    private int daysBeforeArrival;

    @Basic
    @javax.persistence.Column(name = "DaysBeforeArrival")
    public int getDaysBeforeArrival() {
        return daysBeforeArrival;
    }

    public void setDaysBeforeArrival(int daysBeforeArrival) {
        this.daysBeforeArrival = daysBeforeArrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancellationconditionEntity that = (CancellationconditionEntity) o;
        return cancellationConditionId == that.cancellationConditionId &&
                cancellationFee == that.cancellationFee &&
                daysBeforeArrival == that.daysBeforeArrival;
    }

    @Override
    public int hashCode() {

        return Objects.hash(cancellationConditionId, cancellationFee, daysBeforeArrival);
    }
}
