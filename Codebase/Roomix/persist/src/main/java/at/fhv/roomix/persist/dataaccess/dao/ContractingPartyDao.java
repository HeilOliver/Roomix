package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.ContractingPartyEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * ContractingParty
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContractingPartyDao extends AbstractDao<ContractingPartyEntity, Integer> {
    public ContractingPartyDao() {
        super(ContractingPartyEntity.class);
    }
}
