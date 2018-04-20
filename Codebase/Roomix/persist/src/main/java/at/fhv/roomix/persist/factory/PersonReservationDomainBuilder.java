package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.PersonReservationDomain;
import at.fhv.roomix.persist.PersonReservationDao;
import at.fhv.roomix.persist.model.PersonReservationEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class PersonReservationDomainBuilder extends AbstractDomainBuilder<PersonReservationDomain, PersonReservationEntity>
            implements IAbstractDomainBuilder<PersonReservationDomain, PersonReservationEntity>, IProxy<PersonReservationDomain, Integer> {

    private PersonReservationDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    PersonReservationDomainBuilder(){}

    private static IProxy<PersonReservationDomain, Integer> lazyInstance;
    private static Supplier<IAbstractDomainBuilder<PersonReservationDomain, PersonReservationEntity>> supplier;

    public static IAbstractDomainBuilder<PersonReservationDomain, PersonReservationEntity> getInstance() {
        if (supplier == null) return new PersonReservationDomainBuilder(PersonReservationDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(
            Supplier<IAbstractDomainBuilder<PersonReservationDomain, PersonReservationEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

    public static IProxy<PersonReservationDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new PersonReservationDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected PersonReservationDomain mapEntityToDomain(PersonReservationEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        PersonReservationDomain personReservationDomain = modelMapper.map(entity, PersonReservationDomain.class);
        return personReservationDomain;
    }

    @Override
    protected PersonReservationEntity mapDomainToEntity(PersonReservationDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        PersonReservationEntity personReservationEntity = modelMapper.map(domain, PersonReservationEntity.class);
        return personReservationEntity;
    }

    @Override
    public PersonReservationDomain get(int id) {
        return new PersonReservationDomainBuilder(PersonReservationDao::registerAtDao).
                getById(id, PersonReservationEntity.class);
    }

    @Override
    public List<PersonReservationDomain> getAll() {
        return new PersonReservationDomainBuilder(PersonReservationDao::registerAtDao).
                loadAll(PersonReservationEntity.class);
    }

    @Override
    public void set(PersonReservationDomain domainObject) {
        new PersonReservationDomainBuilder(PersonReservationDao::registerAtDao).
                save(PersonReservationEntity.class, domainObject);
    }

    @Override
    public PersonReservationDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<PersonReservationDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new PersonReservationDomainBuilder(PersonReservationDao::registerAtDao).
                loadByForeignKey(PersonReservationEntity.class, key, referencedColumn);
    }
}
