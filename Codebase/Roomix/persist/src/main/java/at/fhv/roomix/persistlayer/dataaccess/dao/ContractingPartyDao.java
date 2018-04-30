package at.fhv.roomix.persistlayer.dataaccess.dao;

import at.fhv.roomix.persist.model.ContractingPartyEntity;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess.dao
 * ContractingPartyDao
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContractingPartyDao extends AbstractDao<ContractingPartyEntity, Integer> {
    public ContractingPartyDao() {
        super(ContractingPartyEntity.class);
    }
}
