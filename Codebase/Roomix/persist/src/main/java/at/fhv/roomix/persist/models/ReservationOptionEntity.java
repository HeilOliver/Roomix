package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ReservationOption", schema = "Roomix", catalog = "")
public class ReservationOptionEntity {
    private int optionId;
    private int daysBeforeArrival;
    private String optionDescription;
    private String optionStatus;
    private int optionPercentage;
    private Collection<ReservationEntity> reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OptionID")
    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    @Basic
    @Column(name = "DaysBeforeArrival")
    public int getDaysBeforeArrival() {
        return daysBeforeArrival;
    }

    public void setDaysBeforeArrival(int daysBeforeArrival) {
        this.daysBeforeArrival = daysBeforeArrival;
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
    public String getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(String optionStatus) {
        this.optionStatus = optionStatus;
    }

    @Basic
    @Column(name = "OptionPercentage")
    public int getOptionPercentage() {
        return optionPercentage;
    }

    public void setOptionPercentage(int optionPercentage) {
        this.optionPercentage = optionPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationOptionEntity that = (ReservationOptionEntity) o;
        return optionId == that.optionId &&
                daysBeforeArrival == that.daysBeforeArrival &&
                optionPercentage == that.optionPercentage &&
                Objects.equals(optionDescription, that.optionDescription) &&
                Objects.equals(optionStatus, that.optionStatus) &&
                Objects.equals(reservations, that.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, daysBeforeArrival, optionDescription, optionStatus);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "option")
    public Collection<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<ReservationEntity> reservationsByOptionId) {
        this.reservations = reservationsByOptionId;
    }

}
