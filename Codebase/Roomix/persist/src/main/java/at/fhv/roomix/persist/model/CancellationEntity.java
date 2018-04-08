package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cancellation", schema = "roomix", catalog = "")
public class CancellationEntity {
    private int cancellationId;
    private int cancellationCondition;
    private Timestamp date;
    private String description;
    private CancellationconditionEntity cancellationconditionByCancellationCondition;
    private Collection<ReservationunitEntity> reservationunitsByCancellationId;

    @Id
    @Column(name = "CancellationID")
    public int getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(int cancellationId) {
        this.cancellationId = cancellationId;
    }

    @Basic
    @Column(name = "CancellationCondition", insertable=false, updatable=false)
    public int getCancellationCondition() {
        return cancellationCondition;
    }

    public void setCancellationCondition(int cancellationCondition) {
        this.cancellationCondition = cancellationCondition;
    }

    @Basic
    @Column(name = "Date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Description")
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

    @ManyToOne
    @JoinColumn(name = "CancellationCondition", referencedColumnName = "CancellationConditionID", nullable = false)
    public CancellationconditionEntity getCancellationconditionByCancellationCondition() {
        return cancellationconditionByCancellationCondition;
    }

    public void setCancellationconditionByCancellationCondition(CancellationconditionEntity cancellationconditionByCancellationCondition) {
        this.cancellationconditionByCancellationCondition = cancellationconditionByCancellationCondition;
    }

    @OneToMany(mappedBy = "cancellationByCancelation")
    public Collection<ReservationunitEntity> getReservationunitsByCancellationId() {
        return reservationunitsByCancellationId;
    }

    public void setReservationunitsByCancellationId(Collection<ReservationunitEntity> reservationunitsByCancellationId) {
        this.reservationunitsByCancellationId = reservationunitsByCancellationId;
    }
}
