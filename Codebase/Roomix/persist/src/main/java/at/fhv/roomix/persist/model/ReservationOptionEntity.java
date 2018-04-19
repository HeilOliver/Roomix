package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ReservationOption", schema = "Roomix", catalog = "")
public class ReservationOptionEntity {
    private int optionId;
    private Date optionDueDate;
    private String optionDescription;
    private byte optionStatus;
    private Collection<ReservationEntity> reservationsByOptionId;

    @Id
    @Column(name = "OptionID")
    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    @Basic
    @Column(name = "OptionDueDate")
    public Date getOptionDueDate() {
        return optionDueDate;
    }

    public void setOptionDueDate(Date optionDueDate) {
        this.optionDueDate = optionDueDate;
    }

    @Basic
    @Column(name = "OptionDescription")
    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    @Basic
    @Column(name = "OptionStatus")
    public byte getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(byte optionStatus) {
        this.optionStatus = optionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationOptionEntity that = (ReservationOptionEntity) o;
        return optionId == that.optionId &&
                optionStatus == that.optionStatus &&
                Objects.equals(optionDueDate, that.optionDueDate) &&
                Objects.equals(optionDescription, that.optionDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(optionId, optionDueDate, optionDescription, optionStatus);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "reservationOptionByReservationOption")
    public Collection<ReservationEntity> getReservationUnitsByOptionId() {
        return reservationsByOptionId;
    }

    public void setReservationUnitsByOptionId(Collection<ReservationEntity> reservationsByOptionId) {
        this.reservationsByOptionId = reservationsByOptionId;
    }
}
