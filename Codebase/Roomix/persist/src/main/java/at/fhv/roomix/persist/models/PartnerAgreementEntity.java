package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PartnerAgreement", schema = "Roomix", catalog = "")
public class PartnerAgreementEntity {
    private int agreementId;
    private int cancellationCondition;
    private Date startDate;
    private Date expiringDate;
    private Integer roomCategory;
    private Integer countRoomCategory;
    private int discount;
    private ContractingPartyEntity contractingParty;
    private CancellationConditionEntity cancellationConditionByCancellationCondition;
    private RoomCategoryEntity roomCategoryByRoomCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgreementID")
    public int getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
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
    @Column(name = "StartDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "ExpiringDate")
    public Date getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(Date expiringDate) {
        this.expiringDate = expiringDate;
    }

    @Basic
    @Column(name = "RoomCategory", insertable = false, updatable = false)
    public Integer getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(Integer roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Basic
    @Column(name = "CountRoomCategory")
    public Integer getCountRoomCategory() {
        return countRoomCategory;
    }

    public void setCountRoomCategory(Integer countRoomCategory) {
        this.countRoomCategory = countRoomCategory;
    }

    @Basic
    @Column(name = "Discount")
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerAgreementEntity that = (PartnerAgreementEntity) o;
        return agreementId == that.agreementId &&
                cancellationCondition == that.cancellationCondition &&
                discount == that.discount &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(expiringDate, that.expiringDate) &&
                Objects.equals(roomCategory, that.roomCategory) &&
                Objects.equals(countRoomCategory, that.countRoomCategory);
    }

    @Override
    public int hashCode() {

        return Objects.hash(agreementId, cancellationCondition, startDate, expiringDate, roomCategory, countRoomCategory, discount);
    }

    @ManyToOne
    @JoinColumn(name = "ContractingParty", referencedColumnName = "ContractingPartyID", nullable = false)
    public ContractingPartyEntity getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(ContractingPartyEntity contractingPartyByContractingParty) {
        this.contractingParty = contractingPartyByContractingParty;
    }

    @ManyToOne
    @JoinColumn(name = "CancellationCondition", referencedColumnName = "CancellationConditionID", nullable = false)
    public CancellationConditionEntity getCancellationConditionByCancellationCondition() {
        return cancellationConditionByCancellationCondition;
    }

    public void setCancellationConditionByCancellationCondition(CancellationConditionEntity cancellationConditionByCancellationCondition) {
        this.cancellationConditionByCancellationCondition = cancellationConditionByCancellationCondition;
    }

    @ManyToOne
    @JoinColumn(name = "RoomCategory", referencedColumnName = "RoomCategoryID")
    public RoomCategoryEntity getRoomCategoryByRoomCategory() {
        return roomCategoryByRoomCategory;
    }

    public void setRoomCategoryByRoomCategory(RoomCategoryEntity roomCategoryByRoomCategory) {
        this.roomCategoryByRoomCategory = roomCategoryByRoomCategory;
    }
}
