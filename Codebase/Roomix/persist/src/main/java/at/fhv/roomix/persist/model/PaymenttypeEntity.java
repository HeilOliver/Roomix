package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "paymenttype", schema = "roomix", catalog = "")
public class PaymenttypeEntity {
    private int paymentTypeId;
    private String paymentTypeDescription;
    private Collection<PaymentEntity> paymentsByPaymentTypeId;

    @Id
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
        PaymenttypeEntity that = (PaymenttypeEntity) o;
        return paymentTypeId == that.paymentTypeId &&
                Objects.equals(paymentTypeDescription, that.paymentTypeDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(paymentTypeId, paymentTypeDescription);
    }

    @OneToMany(mappedBy = "paymenttypeByPaymentType")
    public Collection<PaymentEntity> getPaymentsByPaymentTypeId() {
        return paymentsByPaymentTypeId;
    }

    public void setPaymentsByPaymentTypeId(Collection<PaymentEntity> paymentsByPaymentTypeId) {
        this.paymentsByPaymentTypeId = paymentsByPaymentTypeId;
    }
}
