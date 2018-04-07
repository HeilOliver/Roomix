package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "partneragreement", schema = "roomix", catalog = "")
public class PartneragreementEntity {
    private int agreementId;
    private int contractingParty;
    private int cancellationCondition;
    private Date startDate;
    private Date expiringDate;
    private int discount;
    private ContractingpartyEntity contractingpartyByContractingParty;
    private CancellationconditionEntity cancellationconditionByCancellationCondition;

    @Id
    @Column(name = "AgreementID")
    public int getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
    }

    @Basic
    @Column(name = "ContractingParty")
    public int getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(int contractingParty) {
        this.contractingParty = contractingParty;
    }

    @Basic
    @Column(name = "CancellationCondition")
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
        PartneragreementEntity that = (PartneragreementEntity) o;
        return agreementId == that.agreementId &&
                contractingParty == that.contractingParty &&
                cancellationCondition == that.cancellationCondition &&
                discount == that.discount &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(expiringDate, that.expiringDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(agreementId, contractingParty, cancellationCondition, startDate, expiringDate, discount);
    }

    @ManyToOne
    @JoinColumn(name = "ContractingParty", referencedColumnName = "ContractingPartyID", nullable = false)
    public ContractingpartyEntity getContractingpartyByContractingParty() {
        return contractingpartyByContractingParty;
    }

    public void setContractingpartyByContractingParty(ContractingpartyEntity contractingpartyByContractingParty) {
        this.contractingpartyByContractingParty = contractingpartyByContractingParty;
    }

    @ManyToOne
    @JoinColumn(name = "CancellationCondition", referencedColumnName = "CancellationConditionID", nullable = false)
    public CancellationconditionEntity getCancellationconditionByCancellationCondition() {
        return cancellationconditionByCancellationCondition;
    }

    public void setCancellationconditionByCancellationCondition(CancellationconditionEntity cancellationconditionByCancellationCondition) {
        this.cancellationconditionByCancellationCondition = cancellationconditionByCancellationCondition;
    }
}
