package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Reservation", schema = "Roomix", catalog = "")
public class ReservationEntity {
    private int reservationId;
    private int contractingParty;
    private int paymentType;
    private String reservationStatus;
    private String reservationComment;
    private Collection<InvoicePositionEntity> invoicePositionsByReservationId;
    private Collection<PersonReservationEntity> personReservationsByReservationId;
    private ContractingPartyEntity contractingPartyByContractingParty;
    private PaymentTypeEntity paymentTypeByPaymentType;
    private Collection<ReservationUnitEntity> reservationUnitsByReservationId;

    @Id
    @Column(name = "ReservationID")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Basic
    @Column(name = "ContractingParty", insertable = false, updatable = false)
    public int getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(int contractingParty) {
        this.contractingParty = contractingParty;
    }

    @Basic
    @Column(name = "PaymentType", insertable = false, updatable = false)
    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "ReservationStatus")
    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Basic
    @Column(name = "ReservationComment")
    public String getReservationComment() {
        return reservationComment;
    }

    public void setReservationComment(String reservationComment) {
        this.reservationComment = reservationComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntity that = (ReservationEntity) o;
        return reservationId == that.reservationId &&
                contractingParty == that.contractingParty &&
                paymentType == that.paymentType &&
                Objects.equals(reservationStatus, that.reservationStatus) &&
                Objects.equals(reservationComment, that.reservationComment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservationId, contractingParty, paymentType, reservationStatus, reservationComment);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationByReservation")
    public Collection<InvoicePositionEntity> getInvoicePositionsByReservationId() {
        return invoicePositionsByReservationId;
    }

    public void setInvoicePositionsByReservationId(Collection<InvoicePositionEntity> invoicePositionsByReservationId) {
        this.invoicePositionsByReservationId = invoicePositionsByReservationId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationByReservation")
    public Collection<PersonReservationEntity> getPersonReservationsByReservationId() {
        return personReservationsByReservationId;
    }

    public void setPersonReservationsByReservationId(Collection<PersonReservationEntity> personReservationsByReservationId) {
        this.personReservationsByReservationId = personReservationsByReservationId;
    }

    @ManyToOne
    @JoinColumn(name = "ContractingParty", referencedColumnName = "ContractingPartyID", nullable = false)
    public ContractingPartyEntity getContractingPartyByContractingParty() {
        return contractingPartyByContractingParty;
    }

    public void setContractingPartyByContractingParty(ContractingPartyEntity contractingPartyByContractingParty) {
        this.contractingPartyByContractingParty = contractingPartyByContractingParty;
    }

    @ManyToOne
    @JoinColumn(name = "PaymentType", referencedColumnName = "PaymentTypeID", nullable = false)
    public PaymentTypeEntity getPaymentTypeByPaymentType() {
        return paymentTypeByPaymentType;
    }

    public void setPaymentTypeByPaymentType(PaymentTypeEntity paymentTypeByPaymentType) {
        this.paymentTypeByPaymentType = paymentTypeByPaymentType;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationByReservation")
    public Collection<ReservationUnitEntity> getReservationUnitsByReservationId() {
        return reservationUnitsByReservationId;
    }

    public void setReservationUnitsByReservationId(Collection<ReservationUnitEntity> reservationUnitsByReservationId) {
        this.reservationUnitsByReservationId = reservationUnitsByReservationId;
    }
}
