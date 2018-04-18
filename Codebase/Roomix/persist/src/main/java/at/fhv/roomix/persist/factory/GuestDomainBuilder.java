package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persist.model.ContractingPartyEntity;
import at.fhv.roomix.persist.model.CreditCardEntity;
import at.fhv.roomix.persist.model.PersonEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GuestDomainBuilder extends AbstractDomainBuilder<GuestDomain, ContactEntity>
        implements IAbstractDomainBuilder<GuestDomain, ContactEntity> {
    /* Dependency Injection */
    private static Supplier<IAbstractDomainBuilder<GuestDomain, ContactEntity>> supplier;

    private GuestDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    GuestDomainBuilder() {
    }

    public static IAbstractDomainBuilder<GuestDomain, ContactEntity> getInstance() {
        if (supplier == null) return new GuestDomainBuilder(ContactDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<GuestDomain, ContactEntity>> builderSupplier) {
        supplier = builderSupplier;
    }


    @Override
    protected GuestDomain mapEntityToDomain(ContactEntity contactEntity) {

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ContactEntity, GuestDomain> typeMap = modelMapper.createTypeMap(ContactEntity.class, GuestDomain.class);
        typeMap.addMappings(mapper -> mapper.skip(GuestDomain::setContractingPartiesByContactId));
        typeMap.addMappings(mapper -> mapper.skip(GuestDomain::setPeopleByContactId));
        if(typeMap.getConverter() == null){
            logger.fatal("no converter returned by typemap");
        }
        GuestDomain guestDomain = modelMapper.map(contactEntity, GuestDomain.class);

        /* Mapping of collection entities to domain objects */
        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();

        /* Map flat entities */
        put(ContactNoteDomain.class, contactEntity::getContactNotesByContactId,
                guestDomain::setContactNotesByContactId, mapping);
        put(CreditCardDomain.class, contactEntity::getCreditCardsByContactId,
                guestDomain::setCreditCardsByContactId, mapping);

        /* Init proxy for lazy loading of deep entities */
        Proxy<Collection<ContractingPartyDomain>, Integer> contractingPartyProxy =
                new Proxy<>(guestDomain.getContactId(),
                        key -> ContractingPartyDomainBuilder.getLazyInstance().lazyLoadCollection(key)
                );
        guestDomain.setContractingPartyDomainBuilderProxy(contractingPartyProxy);

        mapAllCollections(mapping);
        return guestDomain;
    }

    @Override
    protected ContactEntity mapDomainToEntity(GuestDomain domain) {

        ModelMapper modelMapper = new ModelMapper();
        ContactEntity contactEntity = modelMapper.map(domain, ContactEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();

        put(ContactEntity.class, domain::getContactNotesByContactId, contactEntity::setContactNotesByContactId, mapping);
        put(CreditCardEntity.class, domain::getCreditCardsByContactId, contactEntity::setCreditCardsByContactId, mapping);
        put(ContractingPartyEntity.class, domain::getContractingPartiesByContactId,
                contactEntity::setContractingPartiesByContactId, mapping);
        put(PersonEntity.class, domain::getPeopleByContactId, contactEntity::setPeopleByContactId, mapping);

        mapAllCollections(mapping);

        return contactEntity;
    }

    @Override
    public GuestDomain get(int id) {
        return new GuestDomainBuilder(ContactDao::registerAtDao).getById(id, ContactEntity.class);
    }

    @Override
    public List<GuestDomain> getAll() {
        return new GuestDomainBuilder(ContactDao::registerAtDao).loadAll(ContactEntity.class);
    }

    @Override
    public void set(GuestDomain domainObject) {
        new GuestDomainBuilder(ContactDao::registerAtDao).save(ContactEntity.class, domainObject);
    }
}
