package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.RoomCategoryPriceDomain;
import at.fhv.roomix.persist.RoomCategoryPriceDao;
import at.fhv.roomix.persist.model.RoomCategoryPriceEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class RoomCategoryPriceDomainBuilder
        extends AbstractDomainBuilder<RoomCategoryPriceDomain, RoomCategoryPriceEntity>
        implements IAbstractDomainBuilder<RoomCategoryPriceDomain, RoomCategoryPriceEntity>,
        IProxy<RoomCategoryPriceDomain, Integer> {

    private RoomCategoryPriceDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    RoomCategoryPriceDomainBuilder(){}

    private static Supplier<IAbstractDomainBuilder<RoomCategoryPriceDomain, RoomCategoryPriceEntity>> supplier;
    private static IProxy<RoomCategoryPriceDomain, Integer> lazyInstance;

    public static IProxy<RoomCategoryPriceDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new RoomCategoryPriceDomainBuilder();
        }
        return lazyInstance;
    }

    public static IAbstractDomainBuilder<RoomCategoryPriceDomain, RoomCategoryPriceEntity> getInstance() {
        if (supplier == null) return new RoomCategoryPriceDomainBuilder(RoomCategoryPriceDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<RoomCategoryPriceDomain, RoomCategoryPriceEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    @Override
    protected RoomCategoryPriceDomain mapEntityToDomain(RoomCategoryPriceEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        RoomCategoryPriceDomain roomCategoryPriceDomain = modelMapper.map(entity, RoomCategoryPriceDomain.class);
        return roomCategoryPriceDomain;
    }

    @Override
    protected RoomCategoryPriceEntity mapDomainToEntity(RoomCategoryPriceDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        RoomCategoryPriceEntity roomCategoryPriceEntity = modelMapper.map(domain, RoomCategoryPriceEntity.class);
        return roomCategoryPriceEntity;
    }

    @Override
    public RoomCategoryPriceDomain get(int id) {
        return new RoomCategoryPriceDomainBuilder(RoomCategoryPriceDao::registerAtDao).
                getById(id, RoomCategoryPriceEntity.class);
    }

    @Override
    public List<RoomCategoryPriceDomain> getAll() {
        return new RoomCategoryPriceDomainBuilder(RoomCategoryPriceDao::registerAtDao).
                loadAll(RoomCategoryPriceEntity.class);
    }

    @Override
    public void set(RoomCategoryPriceDomain domainObject) {
        new RoomCategoryPriceDomainBuilder(RoomCategoryPriceDao::registerAtDao).
                save(RoomCategoryPriceEntity.class, domainObject);
    }

    @Override
    public RoomCategoryPriceDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<RoomCategoryPriceDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new RoomCategoryPriceDomainBuilder(RoomCategoryPriceDao::registerAtDao).
                loadByForeignKey(RoomCategoryPriceEntity.class, key, referencedColumn);
    }
}
