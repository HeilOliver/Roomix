package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ContractingParty", schema = "roomix", catalog = "")
public class ContractingpartyEntity {
    private int contractingPartyId;
    private String contractingPartyType;
    private int contact;
    private ContactEntity contactByContact;
    private Collection<PartneragreementEntity> partneragreementsByContractingPartyId;
    private Collection<ReservationEntity> reservationsByContractingPartyId;

    @Id
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

    @Basic
    @Column(name = "Contact", insertable = false, updatable = false)
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

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID", nullable = false)
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contractingpartyByContractingParty")
    public Collection<PartneragreementEntity> getPartneragreementsByContractingPartyId() {
        return partneragreementsByContractingPartyId;
    }

    public void setPartneragreementsByContractingPartyId(Collection<PartneragreementEntity> partneragreementsByContractingPartyId) {
        this.partneragreementsByContractingPartyId = partneragreementsByContractingPartyId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contractingpartyByContractingParty")
    public Collection<ReservationEntity> getReservationsByContractingPartyId() {
        return reservationsByContractingPartyId;
    }

    public void setReservationsByContractingPartyId(Collection<ReservationEntity> reservationsByContractingPartyId) {
        this.reservationsByContractingPartyId = reservationsByContractingPartyId;
    }
}
