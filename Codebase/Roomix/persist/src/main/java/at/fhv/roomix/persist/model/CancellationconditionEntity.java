package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "CancellationCondition", schema = "roomix", catalog = "")
public class CancellationconditionEntity {
    private int cancellationConditionId;
    private int cancellationFee;
    private int daysBeforeArrival;
    private Collection<CancellationEntity> cancellationsByCancellationConditionId;
    private Collection<PartneragreementEntity> partneragreementsByCancellationConditionId;

    @Id
    @Column(name = "CancellationConditionID")
    public int getCancellationConditionId() {
        return cancellationConditionId;
    }

    public void setCancellationConditionId(int cancellationConditionId) {
        this.cancellationConditionId = cancellationConditionId;
    }

    @Basic
    @Column(name = "CancellationFee")
    public int getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(int cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    @Basic
    @Column(name = "DaysBeforeArrival")
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

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "cancellationconditionByCancellationCondition")
    public Collection<CancellationEntity> getCancellationsByCancellationConditionId() {
        return cancellationsByCancellationConditionId;
    }

    public void setCancellationsByCancellationConditionId(Collection<CancellationEntity> cancellationsByCancellationConditionId) {
        this.cancellationsByCancellationConditionId = cancellationsByCancellationConditionId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "cancellationconditionByCancellationCondition")
    public Collection<PartneragreementEntity> getPartneragreementsByCancellationConditionId() {
        return partneragreementsByCancellationConditionId;
    }

    public void setPartneragreementsByCancellationConditionId(Collection<PartneragreementEntity> partneragreementsByCancellationConditionId) {
        this.partneragreementsByCancellationConditionId = partneragreementsByCancellationConditionId;
    }
}
