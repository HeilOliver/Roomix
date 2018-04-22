package at.fhv.roomix.domain.guest.model;

public class RoomCategoryMetaData {

    private int numberOfConfirmedReservations;
    private int numberOfUnconfirmedReservations;
    private int numberOfOccupiedRooms;
    private int totalNumberOfRooms;

    private int pricePerDay;
    private int agreementDiscount = 0; // percent (0-100)

    private int contingent;

    public int getAgreementDiscount() {
        return agreementDiscount;
    }

    public void setAgreementDiscount(int agreementDiscount) {
        this.agreementDiscount = agreementDiscount;
    }

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

    public int getTotalNumberOfRooms() {
        return totalNumberOfRooms;
    }

    public void setTotalNumberOfRooms(int totalNumberOfRooms) {
        this.totalNumberOfRooms = totalNumberOfRooms;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getContingent() {
        return contingent;
    }

    public void setContingent(int contingent) {
        this.contingent = contingent;
    }

}
