package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Cancellation", schema = "Roomix", catalog = "")
public class CancellationEntity {
    private int cancellationId;
    private int cancellationCondition;
    private Timestamp cancellationDate;
    private String description;
    private CancellationConditionEntity cancellationConditionByCancellationCondition;
    private Collection<ReservationUnitEntity> reservationUnitsByCancellationId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CancellationID")
    public int getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(int cancellationId) {
        this.cancellationId = cancellationId;
    }

    @Basic
    @Column(name = "CancellationCondition", insertable = false, updatable = false)
    public int getCancellationCondition() {
        return cancellationCondition;
    }

    public void setCancellationCondition(int cancellationCondition) {
        this.cancellationCondition = cancellationCondition;
    }

    @Basic
    @Column(name = "CancellationDate")
    public Timestamp getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Timestamp cancellationDate) {
        this.cancellationDate = cancellationDate;
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
                Objects.equals(cancellationDate, that.cancellationDate) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cancellationId, cancellationCondition, cancellationDate, description);
    }

    @ManyToOne
    @JoinColumn(name = "CancellationCondition", referencedColumnName = "CancellationConditionID", nullable = false)
    public CancellationConditionEntity getCancellationConditionByCancellationCondition() {
        return cancellationConditionByCancellationCondition;
    }

    public void setCancellationConditionByCancellationCondition(CancellationConditionEntity cancellationConditionByCancellationCondition) {
        this.cancellationConditionByCancellationCondition = cancellationConditionByCancellationCondition;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "cancellation")
    public Collection<ReservationUnitEntity> getReservationUnitsByCancellationId() {
        return reservationUnitsByCancellationId;
    }

    public void setReservationUnitsByCancellationId(Collection<ReservationUnitEntity> reservationUnitsByCancellationId) {
        this.reservationUnitsByCancellationId = reservationUnitsByCancellationId;
    }
}
