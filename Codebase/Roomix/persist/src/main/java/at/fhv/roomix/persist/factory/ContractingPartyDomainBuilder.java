package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.ContractingPartyDomain;
import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.persist.model.ContractingPartyEntity;

import java.util.Collection;
import java.util.List;

public class ContractingPartyDomainBuilder extends AbstractDomainBuilder<ContractingPartyDomain, ContractingPartyEntity>
        implements IProxy<ContractingPartyDomain, Integer> {


    private static IProxy<ContractingPartyDomain, Integer> lazyInstance;

    public static IProxy<ContractingPartyDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new ContractingPartyDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected ContractingPartyDomain mapEntityToDomain(ContractingPartyEntity entity) {
        return null;
    }

    @Override
    protected ContractingPartyEntity mapDomainToEntity(ContractingPartyDomain domain) {
        return null;
    }

    @Override
    public ContractingPartyDomain get(int id) {
        return null;
    }

    @Override
    public List<ContractingPartyDomain> getAll() {
        return null;
    }

    @Override
    public void set(ContractingPartyDomain domainObject) {

    }

    @Override
    public Collection<ContractingPartyDomain> lazyLoadCollection(Integer key) {
        return null;
    }

    @Override
    public ContractingPartyDomain lazyLoadInstance(Integer key) {
        return null;
    }
}
