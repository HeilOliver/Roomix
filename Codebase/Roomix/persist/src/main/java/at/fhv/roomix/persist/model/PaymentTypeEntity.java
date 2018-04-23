package at.fhv.roomix.persist.model;

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
    private Collection<PaymentEntity> paymentsByPaymentTypeId;
    private Collection<ReservationEntity> reservationsByPaymentTypeId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "paymentTypeByPaymentType")
    public Collection<PaymentEntity> getPaymentsByPaymentTypeId() {
        return paymentsByPaymentTypeId;
    }

    public void setPaymentsByPaymentTypeId(Collection<PaymentEntity> paymentsByPaymentTypeId) {
        this.paymentsByPaymentTypeId = paymentsByPaymentTypeId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "paymentTypeByPaymentType")
    public Collection<ReservationEntity> getReservationsByPaymentTypeId() {
        return reservationsByPaymentTypeId;
    }

    public void setReservationsByPaymentTypeId(Collection<ReservationEntity> reservationsByPaymentTypeId) {
        this.reservationsByPaymentTypeId = reservationsByPaymentTypeId;
    }
}
