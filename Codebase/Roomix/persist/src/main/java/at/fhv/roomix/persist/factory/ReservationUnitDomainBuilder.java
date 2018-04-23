package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.ReservationUnitDomain;
import at.fhv.roomix.persist.ReservationUnitDao;
import at.fhv.roomix.persist.model.ReservationUnitEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class ReservationUnitDomainBuilder extends AbstractDomainBuilder<ReservationUnitDomain, ReservationUnitEntity>
            implements IAbstractDomainBuilder<ReservationUnitDomain, ReservationUnitEntity>, IProxy<ReservationUnitDomain, Integer> {

    private ReservationUnitDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    ReservationUnitDomainBuilder(){}

    private static IProxy<ReservationUnitDomain, Integer> lazyInstance;
    private static Supplier<IAbstractDomainBuilder<ReservationUnitDomain, ReservationUnitEntity>> supplier;

    public static IAbstractDomainBuilder<ReservationUnitDomain, ReservationUnitEntity> getInstance() {
        if (supplier == null) return new ReservationUnitDomainBuilder(ReservationUnitDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<ReservationUnitDomain, ReservationUnitEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    public static IProxy<ReservationUnitDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new ReservationUnitDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected ReservationUnitDomain mapEntityToDomain(ReservationUnitEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ReservationUnitDomain reservationUnitDomain = modelMapper.map(entity, ReservationUnitDomain.class);
        return reservationUnitDomain;
    }

    @Override
    protected ReservationUnitEntity mapDomainToEntity(ReservationUnitDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ReservationUnitEntity reservationUnitEntity= modelMapper.map(domain, ReservationUnitEntity.class);
        return reservationUnitEntity;
    }

    @Override
    public ReservationUnitDomain get(int id) {
        return new ReservationUnitDomainBuilder(ReservationUnitDao::registerAtDao).
                getById(id, ReservationUnitEntity.class);
    }

    @Override
    public List<ReservationUnitDomain> getAll() {
        return new ReservationUnitDomainBuilder(ReservationUnitDao::registerAtDao).
                loadAll(ReservationUnitEntity.class);
    }

    @Override
    public void set(ReservationUnitDomain domainObject) {
        new ReservationUnitDomainBuilder(ReservationUnitDao::registerAtDao).
                save(ReservationUnitEntity.class, domainObject);
    }

    @Override
    public ReservationUnitDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<ReservationUnitDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new ReservationUnitDomainBuilder(ReservationUnitDao::registerAtDao).
                loadByForeignKey(ReservationUnitEntity.class, key, referencedColumn);
    }
}
