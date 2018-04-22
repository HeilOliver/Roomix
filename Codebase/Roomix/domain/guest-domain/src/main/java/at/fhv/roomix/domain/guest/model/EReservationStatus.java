package at.fhv.roomix.domain.guest.model;

public enum EReservationStatus {
    CONFIRMED("CONFIRMED"), UNCONFIRMED("UNCONFIRMED"), CANCELLED("CANCELLED");

    private String str;

    private EReservationStatus(String type){
        str = type;
    }

    public String getStr(){
        return str;
    }
}
