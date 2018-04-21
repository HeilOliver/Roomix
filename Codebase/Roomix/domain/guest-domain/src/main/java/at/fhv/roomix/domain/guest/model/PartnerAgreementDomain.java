package at.fhv.roomix.domain.guest.model;

import java.util.Date;

public class PartnerAgreementDomain {
    private int agreementId;
    private int contractingParty;
    private int cancellationCondition;
    private Date startDate;
    private Date expiringDate;
    private Integer roomCategory;
    private Integer countRoomCategory;
    private int discount;

    private ContractingPartyDomain contractingPartyByContractingParty;
    private CancellationConditionDomain cancellationConditionByCancellationCondition;
    private RoomCategoryDomain roomCategoryByRoomCategory;

    public int getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
    }

    public int getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(int contractingParty) {
        this.contractingParty = contractingParty;
    }

    public int getCancellationCondition() {
        return cancellationCondition;
    }

    public void setCancellationCondition(int cancellationCondition) {
        this.cancellationCondition = cancellationCondition;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(Date expiringDate) {
        this.expiringDate = expiringDate;
    }

    public Integer getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(Integer roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getCountRoomCategory() {
        return countRoomCategory;
    }

    public void setCountRoomCategory(Integer countRoomCategory) {
        this.countRoomCategory = countRoomCategory;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ContractingPartyDomain getContractingPartyByContractingParty() {
        return contractingPartyByContractingParty;
    }

    public void setContractingPartyByContractingParty(ContractingPartyDomain contractingPartyByContractingParty) {
        this.contractingPartyByContractingParty = contractingPartyByContractingParty;
    }

    public CancellationConditionDomain getCancellationConditionByCancellationCondition() {
        return cancellationConditionByCancellationCondition;
    }

    public void setCancellationConditionByCancellationCondition(CancellationConditionDomain cancellationConditionByCancellationCondition) {
        this.cancellationConditionByCancellationCondition = cancellationConditionByCancellationCondition;
    }

    public RoomCategoryDomain getRoomCategoryByRoomCategory() {
        return roomCategoryByRoomCategory;
    }

    public void setRoomCategoryByRoomCategory(RoomCategoryDomain roomCategoryByRoomCategory) {
        this.roomCategoryByRoomCategory = roomCategoryByRoomCategory;
    }
}
