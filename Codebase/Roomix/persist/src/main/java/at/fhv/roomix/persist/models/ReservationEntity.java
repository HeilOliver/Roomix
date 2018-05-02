package at.fhv.roomix.persist.models;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Reservation", schema = "Roomix", catalog = "")
public class ReservationEntity {
    private int reservationId;
    private String reservationStatus;
    private String reservationComment;

    private Collection<InvoicePositionEntity> invoicePositions = new HashSet<>();
    private Collection<ReservationUnitEntity> units = new HashSet<>();
    private Collection<PersonEntity> guests = new HashSet<>();

    private ContractingPartyEntity contractingParty;
    private ReservationOptionEntity option;
    private PaymentTypeEntity paymentType;

    public ReservationEntity() {
        reservationStatus = ReservationStatus.NEW.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationID")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<InvoicePositionEntity> getInvoicePositions() {
        return invoicePositions;
    }

    public void setInvoicePositions(Collection<InvoicePositionEntity> invoicePositionEntities) {
        this.invoicePositions = invoicePositionEntities;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<ReservationUnitEntity> getUnits() {
        return units;
    }

    public void setUnits(Collection<ReservationUnitEntity> unitEntities) {
        this.units = unitEntities;
    }

    @ManyToOne
    @JoinColumn(name = "ContractingParty")
    public ContractingPartyEntity getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(ContractingPartyEntity contractingPartyEntity) {
        this.contractingParty = contractingPartyEntity;
    }

    @ManyToOne
    @JoinColumn(name = "PaymentType")
    public PaymentTypeEntity getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeEntity paymentTypeEntity) {
        this.paymentType = paymentTypeEntity;
    }

    @ManyToOne
    @JoinColumn(name = "ReservationOption")
    public ReservationOptionEntity getOption() {
        return option;
    }

    public void setOption(ReservationOptionEntity reservationOptionByReservationOption) {
        this.option = reservationOptionByReservationOption;
    }

    @ManyToMany(mappedBy = "reservations")
    public Collection<PersonEntity> getGuests() {
        return guests;
    }

    public void setGuests(Collection<PersonEntity> guests) {
        this.guests = guests;
    }

    public enum ReservationStatus {
        NEW,
        PAYED,
        PARTLY_FULFILLED,
        FULFILLED
    }
}
