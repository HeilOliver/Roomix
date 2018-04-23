package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.PersonDomain;
import at.fhv.roomix.domain.guest.model.PersonReservationDomain;
import at.fhv.roomix.domain.guest.model.Proxy;
import at.fhv.roomix.persist.PersonDao;
import at.fhv.roomix.persist.model.PersonEntity;
import at.fhv.roomix.persist.model.PersonReservationEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PersonDomainBuilder extends AbstractDomainBuilder<PersonDomain, PersonEntity>
        implements IAbstractDomainBuilder<PersonDomain, PersonEntity> ,IProxy<PersonDomain, Integer> {

    private PersonDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    PersonDomainBuilder() {}

    private static Supplier<IAbstractDomainBuilder<PersonDomain, PersonEntity>> supplier;
    private static IProxy<PersonDomain, Integer> lazyInstance;

    public static IAbstractDomainBuilder<PersonDomain, PersonEntity> getInstance() {
        if (supplier == null) return new PersonDomainBuilder(PersonDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<PersonDomain, PersonEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    public static IProxy<PersonDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new PersonDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected PersonDomain mapEntityToDomain(PersonEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<PersonEntity, PersonDomain>() {
            @Override
            protected void configure() {
                skip().setPersonReservationsByPersonId(null);
            }
        });
        PersonDomain personDomain = modelMapper.map(entity, PersonDomain.class);
        /* TODO: Add all proxies and collection mappings */
        Proxy<Collection<PersonReservationDomain>, Integer> personReservationProxy =
                new Proxy<>(personDomain.getPersonId(),
                        key -> PersonReservationDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "person")
                );
        personDomain.setPersonReservationProxy(personReservationProxy);
        return personDomain;
    }

    @Override
    protected PersonEntity mapDomainToEntity(PersonDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        PersonEntity personEntity = modelMapper.map(domain, PersonEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();
        put(PersonReservationEntity.class, domain::getPersonReservationsByPersonId,
                personEntity::setPersonReservationsByPersonId, mapping);
        mapAllCollections(mapping);

        return personEntity;
    }

    @Override
    public PersonDomain get(int id) {
        return new PersonDomainBuilder(PersonDao::registerAtDao).getById(id, PersonEntity.class);
    }

    @Override
    public List<PersonDomain> getAll() {
        return new PersonDomainBuilder(PersonDao::registerAtDao).loadAll(PersonEntity.class);
    }

    @Override
    public void set(PersonDomain domainObject) {
        new PersonDomainBuilder(PersonDao::registerAtDao).save(PersonEntity.class, domainObject);
    }

    @Override
    public PersonDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<PersonDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new PersonDomainBuilder(PersonDao::registerAtDao).
                loadByForeignKey(PersonEntity.class, key, referencedColumn);
    }
}
