package at.fhv.roomix.ui.view.checkin.edit.contracting_party;

import at.fhv.roomix.ui.common.StringResourceResolver;
import de.saxsys.mvvmfx.InjectResourceBundle;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public enum ContractingPartyType {

    NONE(0, "contractingparty.type.none"),
    PRIVATE(100, "contractingparty.type.private"),
    COMPANY(200, "contractingparty.type.company"),
    TRAVEL_AGENT(300, "contractingparty.type.travelagent");

    private static HashMap<Integer, String> association;
    private String val;

    ContractingPartyType(int key, String value){
        add(key, value);
    }

    private static void add(int key, String value){
        if(association == null){
            association = new HashMap<>();
        }
        association.put(key, value);
    }

    public String getValue(ResourceBundle bundle){
        return StringResourceResolver.getStaticResolve(bundle, val);
    }

    public static String getByKey(int key, ResourceBundle bundle){
        return StringResourceResolver.getStaticResolve(bundle, association.get(key));
    }
    public static int getByValue(String val){
        for (Map.Entry<Integer, String> integerStringEntry : association.entrySet()) {
            if(integerStringEntry.getValue().equals(val)){
                return integerStringEntry.getKey();
            }
        }
        return 0;
    }

}
