package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ContractingpartyEntity;

public class ContractingPartyDao extends AbstractDao<ContractingpartyEntity, Integer> {

    ContractingPartyDao(){
        super(ContractingpartyEntity.class);
    }


}
