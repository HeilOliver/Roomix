package at.fhv.roomix.persist.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ContractingParty", schema = "Roomix", catalog = "")
public class ContractingPartyEntity {
    private int contractingPartyId;
    private String contractingPartyType;
    private ContactEntity contact;
    private Collection<PartnerAgreementEntity> partnerAgreements;
    private Collection<ReservationEntity> reservations;

    public ContractingPartyEntity() {
    }

    public ContractingPartyEntity(ContractingPartyType partyType) {
        contractingPartyType = partyType.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContractingPartyID")
    public int getContractingPartyId() {
        return contractingPartyId;
    }

    public void setContractingPartyId(int contractingPartyId) {
        this.contractingPartyId = contractingPartyId;
    }

    @Basic
    @Column(name = "ContractingPartyType")
    public String getContractingPartyType() {
        return contractingPartyType;
    }

    public void setContractingPartyType(String contractingPartyType) {
        this.contractingPartyType = contractingPartyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractingPartyEntity that = (ContractingPartyEntity) o;
        return contractingPartyId == that.contractingPartyId &&
                contact == that.contact &&
                Objects.equals(contractingPartyType, that.contractingPartyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractingPartyId, contractingPartyType, contact);
    }

    @OneToOne
    @JoinColumn(name = "Contact")
    public ContactEntity getContact() {
        return contact;
    }
    public void setContact(ContactEntity contactByContact) {
        this.contact = contactByContact;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contractingParty")
    public Collection<PartnerAgreementEntity> getPartnerAgreements() {
        return partnerAgreements;
    }

    public void setPartnerAgreements(Collection<PartnerAgreementEntity> agreementEntities) {
        this.partnerAgreements = agreementEntities;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contractingParty")
    public Collection<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<ReservationEntity> reservationsByContractingPartyId) {
        this.reservations = reservationsByContractingPartyId;
    }

    public enum ContractingPartyType {
        INDIVIDUAL,
        COMPANY,
        TRAVEL_AGENCY
    }
}
