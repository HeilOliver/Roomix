package at.fhv.roomix.persist.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "cancellation", schema = "roomix")
public class CancellationEntity {
    private int cancellationId;

    @Id
    @javax.persistence.Column(name = "CancellationID")
    public int getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(int cancellationId) {
        this.cancellationId = cancellationId;
    }

    private int cancellationCondition;

    @Basic
    @javax.persistence.Column(name = "CancellationCondition")
    public int getCancellationCondition() {
        return cancellationCondition;
    }

    public void setCancellationCondition(int cancellationCondition) {
        this.cancellationCondition = cancellationCondition;
    }

    private Timestamp date;

    @Basic
    @javax.persistence.Column(name = "Date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    private String description;

    @Basic
    @javax.persistence.Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancellationEntity that = (CancellationEntity) o;
        return cancellationId == that.cancellationId &&
                cancellationCondition == that.cancellationCondition &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cancellationId, cancellationCondition, date, description);
    }
}
