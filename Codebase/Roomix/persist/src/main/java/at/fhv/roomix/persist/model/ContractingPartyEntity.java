package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ContractingParty", schema = "Roomix", catalog = "")
public class ContractingPartyEntity {
    private int contractingPartyId;
    private String contractingPartyType;
    private int contact;
    private ContactEntity contactByContact;
    private Collection<PartnerAgreementEntity> partnerAgreementsByContractingPartyId;
    private Collection<ReservationEntity> reservationsByContractingPartyId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
        ContractingPartyEntity that = (ContractingPartyEntity) o;
        return contractingPartyId == that.contractingPartyId &&
                contact == that.contact &&
                Objects.equals(contractingPartyType, that.contractingPartyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractingPartyId, contractingPartyType, contact);
    }

    @ManyToOne
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID", nullable = false)
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contractingPartyByContractingParty")
    public Collection<PartnerAgreementEntity> getPartnerAgreementsByContractingPartyId() {
        return partnerAgreementsByContractingPartyId;
    }

    public void setPartnerAgreementsByContractingPartyId(Collection<PartnerAgreementEntity> partnerAgreementsByContractingPartyId) {
        this.partnerAgreementsByContractingPartyId = partnerAgreementsByContractingPartyId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "contractingPartyByContractingParty")
    public Collection<ReservationEntity> getReservationsByContractingPartyId() {
        return reservationsByContractingPartyId;
    }

    public void setReservationsByContractingPartyId(Collection<ReservationEntity> reservationsByContractingPartyId) {
        this.reservationsByContractingPartyId = reservationsByContractingPartyId;
    }
}
