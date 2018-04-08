package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservationoption", schema = "roomix", catalog = "")
public class ReservationoptionEntity {
    private int optionId;
    private int reservation;
    private Date optionDueDate;
    private String optionDescription;
    private byte optionStatus;
    private ReservationEntity reservationByReservation;

    @Id
    @Column(name = "OptionID")
    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    @Basic
    @Column(name = "Reservation", insertable=false, updatable=false)
    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
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
        ReservationoptionEntity that = (ReservationoptionEntity) o;
        return optionId == that.optionId &&
                reservation == that.reservation &&
                optionStatus == that.optionStatus &&
                Objects.equals(optionDueDate, that.optionDueDate) &&
                Objects.equals(optionDescription, that.optionDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(optionId, reservation, optionDueDate, optionDescription, optionStatus);
    }

    @ManyToOne
    @JoinColumn(name = "Reservation", referencedColumnName = "ReservationID", nullable = false)
    public ReservationEntity getReservationByReservation() {
        return reservationByReservation;
    }

    public void setReservationByReservation(ReservationEntity reservationByReservation) {
        this.reservationByReservation = reservationByReservation;
    }
}
