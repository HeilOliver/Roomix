package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.persist.RoomCategoryDao;
import at.fhv.roomix.persist.model.ReservationUnitEntity;
import at.fhv.roomix.persist.model.RoomCategoryEntity;
import at.fhv.roomix.persist.model.RoomCategoryPriceEntity;
import at.fhv.roomix.persist.model.RoomEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RoomCategoryDomainBuilder extends AbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>
            implements IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>,
                       IProxy<RoomCategoryDomain, Integer> {

    private RoomCategoryDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    RoomCategoryDomainBuilder(){}

    private static Supplier<IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>> supplier;
    private static IProxy<RoomCategoryDomain, Integer> lazyInstance;

    public static IProxy<RoomCategoryDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new RoomCategoryDomainBuilder();
        }
        return lazyInstance;
    }

    public static IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity> getInstance() {
        if (supplier == null) return new RoomCategoryDomainBuilder(RoomCategoryDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    @Override
    protected RoomCategoryDomain mapEntityToDomain(RoomCategoryEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<RoomCategoryEntity, RoomCategoryDomain>() {
            @Override
            protected void configure() {
                skip().setRoomCategoryPricesByRoomCategoryId(null);
                skip().setRoomsByRoomCategoryId(null);
                skip().setReservationUnitsByRoomCategoryId(null);
            }
        });
        RoomCategoryDomain roomCategoryDomain = modelMapper.map(entity, RoomCategoryDomain.class);
        Proxy<Collection<RoomCategoryPriceDomain>, Integer> roomCategoryPriceProxy =
                new Proxy<>(roomCategoryDomain.getRoomCategoryId(),
                        key -> RoomCategoryPriceDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "roomCategory")
                );
        Proxy<Collection<RoomDomain>, Integer> roomProxy =
                new Proxy<>(roomCategoryDomain.getRoomCategoryId(),
                        key -> RoomDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "roomCategory")
                );
        Proxy<Collection<ReservationUnitDomain>, Integer> reservationUnitProxy =
                new Proxy<>(roomCategoryDomain.getRoomCategoryId(),
                        key -> ReservationUnitDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "roomCategory")
                );
        roomCategoryDomain.setRoomProxy(roomProxy);
        roomCategoryDomain.setReservationUnitProxy(reservationUnitProxy);
        roomCategoryDomain.setRoomCategoryPriceProxy(roomCategoryPriceProxy);
        return roomCategoryDomain;
    }

    @Override
    protected RoomCategoryEntity mapDomainToEntity(RoomCategoryDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        RoomCategoryEntity roomCategoryEntity = modelMapper.map(domain, RoomCategoryEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();
        put(RoomEntity.class, domain::getRoomsByRoomCategoryId, roomCategoryEntity::setRoomsByRoomCategoryId, mapping);
        put(RoomCategoryPriceEntity.class, domain::getRoomCategoryPricesByRoomCategoryId,
                roomCategoryEntity::setRoomCategoryPricesByRoomCategoryId, mapping);
        put(ReservationUnitEntity.class, domain::getReservationUnitsByRoomCategoryId,
                roomCategoryEntity::setReservationUnitsByRoomCategoryId, mapping);
        mapAllCollections(mapping);
        return roomCategoryEntity;
    }

    @Override
    public RoomCategoryDomain get(int id) {
        return new RoomCategoryDomainBuilder(RoomCategoryDao::registerAtDao).getById(id, RoomCategoryEntity.class);
    }

    @Override
    public List<RoomCategoryDomain> getAll() {
        return new RoomCategoryDomainBuilder(RoomCategoryDao::registerAtDao).loadAll(RoomCategoryEntity.class);
    }

    @Override
    public void set(RoomCategoryDomain domainObject) {
        new RoomCategoryDomainBuilder(RoomCategoryDao::registerAtDao).save(RoomCategoryEntity.class, domainObject);
    }

    @Override
    public RoomCategoryDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<RoomCategoryDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new RoomCategoryDomainBuilder(RoomCategoryDao::registerAtDao).
                loadByForeignKey(RoomCategoryEntity.class, key, referencedColumn);
    }
}
