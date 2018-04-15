package at.fhv.roomix.domain.guest.model;

public class PersonReservationDomain {

    private int reservation;
    private int person;

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }
}
