package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.ContractingPartyDao;
import at.fhv.roomix.persist.models.ContractingPartyEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * ContractingPartyFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContractingPartyFactory extends EntityFactory<ContractingPartyEntity, Integer> {

    private static final Object lock = new Object();
    private static ContractingPartyFactory instance;

    private ContractingPartyFactory() {
        super(ContractingPartyDao::new, 20);
    }

    public static ContractingPartyFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ContractingPartyFactory();
            }
        }
        return instance;
    }
}
