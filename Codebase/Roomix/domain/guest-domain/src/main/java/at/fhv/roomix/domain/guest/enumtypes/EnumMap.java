package at.fhv.roomix.domain.guest.enumtypes;

import java.util.HashMap;

public class EnumMap {

    private static HashMap<Object, ICommonType> map = new HashMap<>();

    public static void put(Object o, ICommonType enumtype){
        map.put(o, enumtype);
    }
    public static ICommonType getByValue(Object value){
        return map.get(value);
    }
}
