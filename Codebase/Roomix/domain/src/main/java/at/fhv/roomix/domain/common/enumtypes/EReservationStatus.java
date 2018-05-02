package at.fhv.roomix.domain.common.enumtypes;

public enum EReservationStatus implements ICommonType<String> {
    CONFIRMED("CONFIRMED"), UNCONFIRMED("UNCONFIRMED"), CANCELLED("CANCELLED");

    private String str;

    EReservationStatus(String type){
        EnumMap.put(type, this);
        str = type;
    }

    @Override
    public String getValue() {
        return str;
    }
}
