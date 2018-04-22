package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.Proxy;
import at.fhv.roomix.domain.guest.model.RoomAssignmentDomain;
import at.fhv.roomix.domain.guest.model.RoomDomain;
import at.fhv.roomix.persist.RoomDao;
import at.fhv.roomix.persist.model.RoomAssignmentEntity;
import at.fhv.roomix.persist.model.RoomEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RoomDomainBuilder extends AbstractDomainBuilder<RoomDomain, RoomEntity>
            implements IAbstractDomainBuilder<RoomDomain, RoomEntity>, IProxy<RoomDomain, Integer> {

    private RoomDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    RoomDomainBuilder(){}

    private static Supplier<IAbstractDomainBuilder<RoomDomain, RoomEntity>> supplier;
    private static IProxy<RoomDomain, Integer> lazyInstance;

    public static IProxy<RoomDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new RoomDomainBuilder();
        }
        return lazyInstance;
    }

    public static IAbstractDomainBuilder<RoomDomain, RoomEntity> getInstance() {
        if (supplier == null) return new RoomDomainBuilder(RoomDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<RoomDomain, RoomEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    @Override
    protected RoomDomain mapEntityToDomain(RoomEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<RoomEntity, RoomDomain>() {
            @Override
            protected void configure() {
                skip().setRoomAssignmentsByRoomId(null);
            }
        });
        RoomDomain roomDomain = modelMapper.map(entity, RoomDomain.class);

        Proxy<Collection<RoomAssignmentDomain>, Integer> roomAssignmentProxy =
                new Proxy<>(roomDomain.getRoomId(), key -> RoomAssignmentDomainBuilder.
                        getLazyInstance().lazyLoadCollection(key, "room")
                );
        roomDomain.setRoomAssignmentProxy(roomAssignmentProxy);
        return roomDomain;
    }

    @Override
    protected RoomEntity mapDomainToEntity(RoomDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        RoomEntity roomEntity = modelMapper.map(domain, RoomEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();
        put(RoomAssignmentEntity.class, domain::getRoomAssignmentsByRoomId,
                roomEntity::setRoomAssignmentsByRoomId, mapping);
        mapAllCollections(mapping);
        return roomEntity;
    }

    @Override
    public RoomDomain get(int id) {
        return new RoomDomainBuilder(RoomDao::registerAtDao).getById(id, RoomEntity.class);
    }

    @Override
    public List<RoomDomain> getAll() {
        return new RoomDomainBuilder(RoomDao::registerAtDao).loadAll(RoomEntity.class);
    }

    @Override
    public void set(RoomDomain domainObject) {
        new RoomDomainBuilder(RoomDao::registerAtDao).save(RoomEntity.class, domainObject);
    }
    @Override
    public RoomDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<RoomDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new RoomDomainBuilder(RoomDao::registerAtDao).
                loadByForeignKey(RoomEntity.class, key, referencedColumn);
    }
}
