package at.fhv.roomix.domain.guest.model;

public class ReservationDomain {
    private int reservationId;
    private int contractingParty;
    private int person;
    private String reservationStatus;



    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(int contractingParty) {
        this.contractingParty = contractingParty;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
