package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.PartnerAgreementDomain;
import at.fhv.roomix.persist.PartnerAgreementDao;
import at.fhv.roomix.persist.model.PartnerAgreementEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class PartnerAgreementDomainBuilder extends AbstractDomainBuilder<PartnerAgreementDomain, PartnerAgreementEntity>
            implements IAbstractDomainBuilder<PartnerAgreementDomain, PartnerAgreementEntity>, IProxy<PartnerAgreementDomain, Integer> {

    private PartnerAgreementDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    PartnerAgreementDomainBuilder(){}

    private static IProxy<PartnerAgreementDomain, Integer> lazyInstance;
    private static Supplier<IAbstractDomainBuilder<PartnerAgreementDomain, PartnerAgreementEntity>> supplier;

    public static IAbstractDomainBuilder<PartnerAgreementDomain, PartnerAgreementEntity> getInstance() {
        if (supplier == null) return new PartnerAgreementDomainBuilder(PartnerAgreementDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(
            Supplier<IAbstractDomainBuilder<PartnerAgreementDomain, PartnerAgreementEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    public static IProxy<PartnerAgreementDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new PartnerAgreementDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected PartnerAgreementDomain mapEntityToDomain(PartnerAgreementEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        PartnerAgreementDomain partnerAgreementDomain = modelMapper.map(entity, PartnerAgreementDomain.class);
        return partnerAgreementDomain;
    }

    @Override
    protected PartnerAgreementEntity mapDomainToEntity(PartnerAgreementDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        PartnerAgreementEntity partnerAgreementEntity = modelMapper.map(domain, PartnerAgreementEntity.class);
        return partnerAgreementEntity;
    }

    @Override
    public PartnerAgreementDomain get(int id) {
        return new PartnerAgreementDomainBuilder(PartnerAgreementDao::registerAtDao).
                getById(id, PartnerAgreementEntity.class);
    }

    @Override
    public List<PartnerAgreementDomain> getAll() {
        return new PartnerAgreementDomainBuilder(PartnerAgreementDao::registerAtDao).
                loadAll(PartnerAgreementEntity.class);
    }

    @Override
    public void set(PartnerAgreementDomain domainObject) {
        new PartnerAgreementDomainBuilder(PartnerAgreementDao::registerAtDao).
                save(PartnerAgreementEntity.class, domainObject);
    }

    @Override
    public PartnerAgreementDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<PartnerAgreementDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new PartnerAgreementDomainBuilder(PartnerAgreementDao::registerAtDao).
                loadByForeignKey(PartnerAgreementEntity.class, key, referencedColumn);
    }
}
