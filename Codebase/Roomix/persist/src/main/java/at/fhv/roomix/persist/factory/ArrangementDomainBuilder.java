package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.ArrangementDomain;
import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.persist.ArrangementDao;
import at.fhv.roomix.persist.model.ArrangementEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class ArrangementDomainBuilder extends AbstractDomainBuilder<ArrangementDomain, ArrangementEntity>
            implements IAbstractDomainBuilder<ArrangementDomain, ArrangementEntity>
                        ,IProxy<ArrangementDomain, Integer> {

    private ArrangementDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    ArrangementDomainBuilder(){}

    private static IProxy<ArrangementDomain, Integer> lazyInstance;
    private static Supplier<IAbstractDomainBuilder<ArrangementDomain, ArrangementEntity>> supplier;

    public static IAbstractDomainBuilder<ArrangementDomain, ArrangementEntity> getInstance() {
        if (supplier == null) return new ArrangementDomainBuilder(ArrangementDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(
            Supplier<IAbstractDomainBuilder<ArrangementDomain, ArrangementEntity>> builderSupplier) {
        supplier = builderSupplier;
    }


    public static IProxy<ArrangementDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new ArrangementDomainBuilder();
        }
        return lazyInstance;
    }
    @Override
    protected ArrangementDomain mapEntityToDomain(ArrangementEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ArrangementDomain arrangementDomain = modelMapper.map(entity, ArrangementDomain.class);
        return arrangementDomain;
    }

    @Override
    protected ArrangementEntity mapDomainToEntity(ArrangementDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ArrangementEntity arrangementEntity = modelMapper.map(domain, ArrangementEntity.class);
        return arrangementEntity;
    }

    @Override
    public ArrangementDomain get(int id) {
        return new ArrangementDomainBuilder(ArrangementDao::registerAtDao).getById(id, ArrangementEntity.class);
    }

    @Override
    public List<ArrangementDomain> getAll() {
        return new ArrangementDomainBuilder(ArrangementDao::registerAtDao).loadAll(ArrangementEntity.class);
    }

    @Override
    public void set(ArrangementDomain domainObject) {
        new ArrangementDomainBuilder(ArrangementDao::registerAtDao).save(ArrangementEntity.class, domainObject);
    }

    @Override
    public ArrangementDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<ArrangementDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return null;
    }
}
