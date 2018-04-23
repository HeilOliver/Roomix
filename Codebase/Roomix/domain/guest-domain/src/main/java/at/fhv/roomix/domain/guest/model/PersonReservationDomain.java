package at.fhv.roomix.domain.guest.model;

public class PersonReservationDomain {

    private ReservationDomain reservationByReservation;
    private PersonDomain personByPerson;


    public ReservationDomain getReservationByReservation() {
        return reservationByReservation;
    }

    public void setReservationByReservation(ReservationDomain reservationByReservation) {
        this.reservationByReservation = reservationByReservation;
    }

    public PersonDomain getPersonByPerson() {
        return personByPerson;
    }

    public void setPersonByPerson(PersonDomain personByPerson) {
        this.personByPerson = personByPerson;
    }
}
