package at.fhv.roomix.domain.common.enumtypes;

public enum EContractingPartyType implements ICommonType<String> {

    INDIVIDUAL("INDIVIDUAL"), COMPANY("COMPANY"), TRAVELAGENCY("TRAVELAGENCY");

    private String str;

    EContractingPartyType(String type){
        EnumMap.put(type, this);
        str = type;
    }

    @Override
    public String getValue() {
        return str;
    }
}
