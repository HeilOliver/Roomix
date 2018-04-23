package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.persist.ContractingPartyDao;
import at.fhv.roomix.persist.model.ContractingPartyEntity;
import at.fhv.roomix.persist.model.PartnerAgreementEntity;
import at.fhv.roomix.persist.model.ReservationEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ContractingPartyDomainBuilder extends AbstractDomainBuilder<ContractingPartyDomain, ContractingPartyEntity>
        implements IAbstractDomainBuilder<ContractingPartyDomain, ContractingPartyEntity> , IProxy<ContractingPartyDomain, Integer> {

    private ContractingPartyDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    ContractingPartyDomainBuilder(){}

    private static IProxy<ContractingPartyDomain, Integer> lazyInstance;
    private static Supplier<IAbstractDomainBuilder<ContractingPartyDomain, ContractingPartyEntity>> supplier;

    public static IAbstractDomainBuilder<ContractingPartyDomain, ContractingPartyEntity> getInstance() {
        if (supplier == null) return new ContractingPartyDomainBuilder(ContractingPartyDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(
            Supplier<IAbstractDomainBuilder<ContractingPartyDomain, ContractingPartyEntity>> builderSupplier) {
        supplier = builderSupplier;
    }


    public static IProxy<ContractingPartyDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new ContractingPartyDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected ContractingPartyDomain mapEntityToDomain(ContractingPartyEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<ContractingPartyEntity, ContractingPartyDomain>() {
            @Override
            protected void configure() {
                skip().setPartnerAgreementsByContractingPartyId(null);
                skip().setReservationsByContractingPartyId(null);
            }
        });
        ContractingPartyDomain contractingPartyDomain = modelMapper.map(entity, ContractingPartyDomain.class);

        Proxy<Collection<PartnerAgreementDomain>, Integer> partnerAgreementProxy = new Proxy<>(
                contractingPartyDomain.getContractingPartyId(),
                key -> PartnerAgreementDomainBuilder.getLazyInstance().
                        lazyLoadCollection(key, "contractingParty")
                );
        Proxy<Collection<ReservationDomain>, Integer> reservationProxy = new Proxy<>(
                contractingPartyDomain.getContractingPartyId(),
                key -> ReservationDomainBuilder.getLazyInstance().
                        lazyLoadCollection(key, "contractingParty")
        );
        contractingPartyDomain.setPartnerAgreementProxy(partnerAgreementProxy);
        contractingPartyDomain.setReservationProxy(reservationProxy);

        return contractingPartyDomain;
    }

    @Override
    protected ContractingPartyEntity mapDomainToEntity(ContractingPartyDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ContractingPartyEntity entity = modelMapper.map(domain, ContractingPartyEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();

        put(PartnerAgreementEntity.class, domain::getPartnerAgreementsByContractingPartyId,
                entity::setPartnerAgreementsByContractingPartyId, mapping);
        put(ReservationEntity.class, domain::getReservationsByContractingPartyId,
                entity::setReservationsByContractingPartyId, mapping);

        mapAllCollections(mapping);

        return entity;
    }

    @Override
    public ContractingPartyDomain get(int id) {
        return new ContractingPartyDomainBuilder(ContractingPartyDao::registerAtDao).
                getById(id, ContractingPartyEntity.class);
    }

    @Override
    public List<ContractingPartyDomain> getAll() {
        return new ContractingPartyDomainBuilder(ContractingPartyDao::registerAtDao).
                loadAll(ContractingPartyEntity.class);
    }

    @Override
    public void set(ContractingPartyDomain domainObject) {
        new ContractingPartyDomainBuilder(ContractingPartyDao::registerAtDao).
                save(ContractingPartyEntity.class, domainObject);
    }

    @Override
    public Collection<ContractingPartyDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new ContractingPartyDomainBuilder(ContractingPartyDao::registerAtDao).
                loadByForeignKey(ContractingPartyEntity.class, key, referencedColumn);
    }

    @Override
    public ContractingPartyDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }
}
