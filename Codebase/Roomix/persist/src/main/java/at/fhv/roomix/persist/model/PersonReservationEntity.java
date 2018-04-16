package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PersonReservation", schema = "Roomix", catalog = "")
@IdClass(PersonReservationEntityPK.class)
public class PersonReservationEntity {
    private int reservation;
    private int person;
    private ReservationEntity reservationByReservation;
    private PersonEntity personByPerson;

    @Id
    @Column(name = "Reservation", insertable = false, updatable = false)
    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    @Id
    @Column(name = "Person", insertable = false, updatable = false)
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
        PersonReservationEntity that = (PersonReservationEntity) o;
        return reservation == that.reservation &&
                person == that.person;
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservation, person);
    }

    @ManyToOne
    @JoinColumn(name = "Reservation", referencedColumnName = "ReservationID", nullable = false, insertable = false, updatable = false)
    public ReservationEntity getReservationByReservation() {
        return reservationByReservation;
    }

    public void setReservationByReservation(ReservationEntity reservationByReservation) {
        this.reservationByReservation = reservationByReservation;
    }

    @ManyToOne
    @JoinColumn(name = "Person", referencedColumnName = "PersonID", nullable = false, insertable = false, updatable = false)
    public PersonEntity getPersonByPerson() {
        return personByPerson;
    }

    public void setPersonByPerson(PersonEntity personByPerson) {
        this.personByPerson = personByPerson;
    }
}
