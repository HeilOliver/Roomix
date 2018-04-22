package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.persist.ReservationDao;
import at.fhv.roomix.persist.model.PersonReservationEntity;
import at.fhv.roomix.persist.model.ReservationEntity;
import at.fhv.roomix.persist.model.ReservationUnitEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ReservationDomainBuilder extends AbstractDomainBuilder<ReservationDomain, ReservationEntity>
        implements IAbstractDomainBuilder<ReservationDomain, ReservationEntity>, IProxy<ReservationDomain, Integer> {

    private ReservationDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    ReservationDomainBuilder(){}

    private static IProxy<ReservationDomain, Integer> lazyInstance;
    private static Supplier<IAbstractDomainBuilder<ReservationDomain, ReservationEntity>> supplier;


    public static IProxy<ReservationDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new ReservationDomainBuilder();
        }
        return lazyInstance;
    }

    public static IAbstractDomainBuilder<ReservationDomain, ReservationEntity> getInstance() {
        if (supplier == null) return new ReservationDomainBuilder(ReservationDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<ReservationDomain, ReservationEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    @Override
    protected ReservationDomain mapEntityToDomain(ReservationEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ReservationEntity, ReservationDomain>() {
            @Override
            protected void configure() {
               skip().setPersonReservationsByReservationId(null);
               skip().setReservationUnitsByReservationId(null);
            }
        });
        ReservationDomain reservationDomain = modelMapper.map(entity, ReservationDomain.class);

        Proxy<Collection<PersonReservationDomain>, Integer> personReservationProxy =
                new Proxy<>(reservationDomain.getReservationId(),
                        key -> PersonReservationDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "Reservation")
                );
        Proxy<Collection<ReservationUnitDomain>, Integer> reservationUnitProxy =
                new Proxy<>(reservationDomain.getReservationId(),
                        key -> ReservationUnitDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "Reservation")
                );
        reservationDomain.setPersonReservationProxy(personReservationProxy);
        reservationDomain.setReservationUnitProxy(reservationUnitProxy);
        return reservationDomain;
    }

    @Override
    protected ReservationEntity mapDomainToEntity(ReservationDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        ReservationEntity reservationEntity = modelMapper.map(domain, ReservationEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();

        put(PersonReservationEntity.class, domain::getPersonReservationsByReservationId,
                reservationEntity::setPersonReservationsByReservationId, mapping);
        put(ReservationUnitEntity.class, domain::getReservationUnitsByReservationId,
                reservationEntity::setReservationUnitsByReservationId, mapping);
        mapAllCollections(mapping);

        return reservationEntity;
    }

    @Override
    public ReservationDomain get(int id) {
        return new ReservationDomainBuilder(ReservationDao::registerAtDao).getById(id, ReservationEntity.class);
    }

    @Override
    public List<ReservationDomain> getAll() {
        return new ReservationDomainBuilder(ReservationDao::registerAtDao).loadAll(ReservationEntity.class);
    }

    @Override
    public void set(ReservationDomain domainObject) {
        new ReservationDomainBuilder(ReservationDao::registerAtDao).save(ReservationEntity.class, domainObject);
    }

    @Override
    public ReservationDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<ReservationDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new ReservationDomainBuilder(ReservationDao::registerAtDao).
                loadByForeignKey(ReservationEntity.class, key, referencedColumn);
    }
}
