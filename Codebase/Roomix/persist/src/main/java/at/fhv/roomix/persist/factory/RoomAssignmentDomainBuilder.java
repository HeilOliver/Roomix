package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.RoomAssignmentDomain;
import at.fhv.roomix.persist.RoomAssignmentDao;
import at.fhv.roomix.persist.model.RoomAssignmentEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class RoomAssignmentDomainBuilder extends AbstractDomainBuilder<RoomAssignmentDomain, RoomAssignmentEntity>
                implements IAbstractDomainBuilder<RoomAssignmentDomain, RoomAssignmentEntity>,
                           IProxy<RoomAssignmentDomain, Integer> {


    private RoomAssignmentDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    RoomAssignmentDomainBuilder(){}

    private static IProxy<RoomAssignmentDomain, Integer> lazyInstance;
    private static Supplier<IAbstractDomainBuilder<RoomAssignmentDomain, RoomAssignmentEntity>> supplier;


    public static IProxy<RoomAssignmentDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new RoomAssignmentDomainBuilder();
        }
        return lazyInstance;
    }

    public static IAbstractDomainBuilder<RoomAssignmentDomain, RoomAssignmentEntity> getInstance() {
        if (supplier == null) return new RoomAssignmentDomainBuilder(RoomAssignmentDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<RoomAssignmentDomain, RoomAssignmentEntity>> builderSupplier) {
        supplier = builderSupplier;
    }


    @Override
    protected RoomAssignmentDomain mapEntityToDomain(RoomAssignmentEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        RoomAssignmentDomain roomAssignmentDomain = modelMapper.map(entity, RoomAssignmentDomain.class);
        return roomAssignmentDomain;
    }

    @Override
    protected RoomAssignmentEntity mapDomainToEntity(RoomAssignmentDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        RoomAssignmentEntity roomAssignmentEntity = modelMapper.map(domain, RoomAssignmentEntity.class);
        return roomAssignmentEntity;
    }

    @Override
    public RoomAssignmentDomain get(int id) {
        return new RoomAssignmentDomainBuilder(RoomAssignmentDao::registerAtDao).
                getById(id, RoomAssignmentEntity.class);
    }

    @Override
    public List<RoomAssignmentDomain> getAll() {
        return new RoomAssignmentDomainBuilder(RoomAssignmentDao::registerAtDao).
                loadAll(RoomAssignmentEntity.class);
    }

    @Override
    public void set(RoomAssignmentDomain domainObject) {
        new RoomAssignmentDomainBuilder(RoomAssignmentDao::registerAtDao).
                save(RoomAssignmentEntity.class, domainObject);
    }

    @Override
    public RoomAssignmentDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<RoomAssignmentDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new RoomAssignmentDomainBuilder(RoomAssignmentDao::registerAtDao).
                loadByForeignKey(RoomAssignmentEntity.class, key, referencedColumn);
    }
}
