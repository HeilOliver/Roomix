package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "CancellationCondition", schema = "Roomix", catalog = "")
public class CancellationConditionEntity {
    private int cancellationConditionId;
    private int cancellationFee;
    private int daysBeforeArrival;
    private Collection<CancellationEntity> cancellationsByCancellationConditionId;
    private Collection<PartnerAgreementEntity> partnerAgreementsByCancellationConditionId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        CancellationConditionEntity that = (CancellationConditionEntity) o;
        return cancellationConditionId == that.cancellationConditionId &&
                cancellationFee == that.cancellationFee &&
                daysBeforeArrival == that.daysBeforeArrival;
    }

    @Override
    public int hashCode() {

        return Objects.hash(cancellationConditionId, cancellationFee, daysBeforeArrival);
    }

    @OneToMany(mappedBy = "cancellationConditionByCancellationCondition")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Collection<CancellationEntity> getCancellationsByCancellationConditionId() {
        return cancellationsByCancellationConditionId;
    }

    public void setCancellationsByCancellationConditionId(Collection<CancellationEntity> cancellationsByCancellationConditionId) {
        this.cancellationsByCancellationConditionId = cancellationsByCancellationConditionId;
    }

    @OneToMany(mappedBy = "cancellationConditionByCancellationCondition")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Collection<PartnerAgreementEntity> getPartnerAgreementsByCancellationConditionId() {
        return partnerAgreementsByCancellationConditionId;
    }

    public void setPartnerAgreementsByCancellationConditionId(Collection<PartnerAgreementEntity> partnerAgreementsByCancellationConditionId) {
        this.partnerAgreementsByCancellationConditionId = partnerAgreementsByCancellationConditionId;
    }
}
