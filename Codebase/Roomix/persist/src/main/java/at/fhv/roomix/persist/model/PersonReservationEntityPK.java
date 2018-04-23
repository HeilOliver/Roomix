package at.fhv.roomix.persist.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PersonReservationEntityPK implements Serializable {
    private int reservation;
    private int person;

    @Column(name = "Reservation")
    @Id
    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    @Column(name = "Person")
    @Id
    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonReservationEntityPK that = (PersonReservationEntityPK) o;
        return reservation == that.reservation &&
                person == that.person;
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservation, person);
    }
}
