package at.fhv.roomix.domain.reservation.helper;

public class OccupationStatusHelper {

    private int numberOfConfirmedReservations;
    private int numberOfUnconfirmedReservations;
    private int numberOfOccupiedRooms;

    public int getNumberOfConfirmedReservations() {
        return numberOfConfirmedReservations;
    }

    public void setNumberOfConfirmedReservations(int numberOfConfirmedReservations) {
        this.numberOfConfirmedReservations = numberOfConfirmedReservations;
    }

    public int getNumberOfUnconfirmedReservations() {
        return numberOfUnconfirmedReservations;
    }

    public void setNumberOfUnconfirmedReservations(int numberOfUnconfirmedReservations) {
        this.numberOfUnconfirmedReservations = numberOfUnconfirmedReservations;
    }

    public int getNumberOfOccupiedRooms() {
        return numberOfOccupiedRooms;
    }

    public void setNumberOfOccupiedRooms(int numberOfOccupiedRooms) {
        this.numberOfOccupiedRooms = numberOfOccupiedRooms;
    }
}
