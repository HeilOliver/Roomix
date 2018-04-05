package at.fhv.roomix.persist.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "contractingparty", schema = "roomix")
public class ContractingpartyEntity {
    private int contractingPartyId;

    @Id
    @javax.persistence.Column(name = "ContractingPartyID")
    public int getContractingPartyId() {
        return contractingPartyId;
    }

    public void setContractingPartyId(int contractingPartyId) {
        this.contractingPartyId = contractingPartyId;
    }

    private String contractingPartyType;

    @Basic
    @javax.persistence.Column(name = "ContractingPartyType")
    public String getContractingPartyType() {
        return contractingPartyType;
    }

    public void setContractingPartyType(String contractingPartyType) {
        this.contractingPartyType = contractingPartyType;
    }

    private int contact;

    @Basic
    @javax.persistence.Column(name = "Contact")
    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractingpartyEntity that = (ContractingpartyEntity) o;
        return contractingPartyId == that.contractingPartyId &&
                contact == that.contact &&
                Objects.equals(contractingPartyType, that.contractingPartyType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contractingPartyId, contractingPartyType, contact);
    }
}
