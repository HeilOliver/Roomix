package at.fhv.roomix.domain.guest.model;

public class ContractingPartyDomain {

    private int contractingPartyId;
    private String contractingPartyType;
    private int contact;

    public int getContractingPartyId() {
        return contractingPartyId;
    }

    public void setContractingPartyId(int contractingPartyId) {
        this.contractingPartyId = contractingPartyId;
    }

    public String getContractingPartyType() {
        return contractingPartyType;
    }

    public void setContractingPartyType(String contractingPartyType) {
        this.contractingPartyType = contractingPartyType;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }


}
