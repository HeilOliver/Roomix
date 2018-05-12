package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;

/**
 * Roomix
 * at.fhv.roomix.controller.mapping
 * ContractingPartyMapping
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContractingPartyMapping implements MapType<ContractingParty, ContactPojo> {
    static {
        Mapper.getInstance().addMapType(new ContactMapping(), Contact.class, ContactPojo.class);
    }

    @Override
    public void map(ContractingParty source, ContactPojo destination, Mapper mapper) throws MappingException {
        mapper.map(source.getContact(), destination);

        // 0 = none, 100 = private, 200 = company, 300 = travelAgent
        switch (source.getType()) {
            case INDIVIDUAL:
                destination.setContractingPartyType(100);
                break;
            case TRAVEL_AGENT:
                destination.setContractingPartyType(300);
                break;
            case COMPANY:
                destination.setContractingPartyType(200);
                break;
        }
    }
}
