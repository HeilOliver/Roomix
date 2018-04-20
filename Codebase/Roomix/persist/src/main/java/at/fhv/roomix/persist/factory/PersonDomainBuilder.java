package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.PersonDomain;
import at.fhv.roomix.persist.PersonDao;
import at.fhv.roomix.persist.model.PersonEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public class PersonDomainBuilder extends AbstractDomainBuilder<PersonDomain, PersonEntity>
        implements IProxy<PersonDomain, Integer> {

    private PersonDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    PersonDomainBuilder() {}

    private static IProxy<PersonDomain, Integer> lazyInstance;

    public static IProxy<PersonDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new PersonDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected PersonDomain mapEntityToDomain(PersonEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        PersonDomain personDomain = modelMapper.map(entity, PersonDomain.class);
        /* TODO: Add proxies and collection mappings */
        return personDomain;
    }

    @Override
    protected PersonEntity mapDomainToEntity(PersonDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        PersonEntity personEntity= modelMapper.map(domain, PersonEntity.class);
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
