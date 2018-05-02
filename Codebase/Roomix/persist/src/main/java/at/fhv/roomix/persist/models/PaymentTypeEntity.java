package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PaymentType", schema = "Roomix", catalog = "")
public class PaymentTypeEntity {
    private int paymentTypeId;
    private String paymentTypeDescription;
    private Collection<PaymentEntity> payments;
    private Collection<ReservationEntity> reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentTypeID")
    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Basic
    @Column(name = "PaymentTypeDescription")
    public String getPaymentTypeDescription() {
        return paymentTypeDescription;
    }

    public void setPaymentTypeDescription(String paymentTypeDescription) {
        this.paymentTypeDescription = paymentTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTypeEntity that = (PaymentTypeEntity) o;
        return paymentTypeId == that.paymentTypeId &&
                Objects.equals(paymentTypeDescription, that.paymentTypeDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(paymentTypeId, paymentTypeDescription);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(Collection<PaymentEntity> paymentsByPaymentTypeId) {
        this.payments = paymentsByPaymentTypeId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<ReservationEntity> reservationsByPaymentTypeId) {
        this.reservations = reservationsByPaymentTypeId;
    }
}
