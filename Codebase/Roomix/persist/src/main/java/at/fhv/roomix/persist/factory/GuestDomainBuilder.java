package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.domain.guest.model.ContractingPartyDomain;
import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class GuestDomainBuilder extends AbstractDomainBuilder<GuestDomain, ContactEntity>
        implements IAbstractDomainBuilder<GuestDomain, ContactEntity>, IProxy<GuestDomain, Integer> {

    /* Dependency Injection */
    private static Supplier<IAbstractDomainBuilder<GuestDomain, ContactEntity>> supplier;
    private static IProxy<GuestDomain, Integer> lazyInstance;

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

    public static IProxy<GuestDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new GuestDomainBuilder();
        }
        return lazyInstance;
    }


    @Override
    protected GuestDomain mapEntityToDomain(ContactEntity contactEntity) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        /* Skip Collections that should be used for lazy loading */
        modelMapper.addMappings(new PropertyMap<ContactEntity, GuestDomain>() {
            @Override
            protected void configure() {
                skip().setContractingPartiesByContactId(null);
                skip().setPeopleByContactId(null);
                skip().setInvoicesByContactId(null);
            }
        });
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
                        key -> ContractingPartyDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "contact"));
        Proxy<Collection<PersonDomain>, Integer> personProxy =
                new Proxy<>(guestDomain.getContactId(),
                        key -> PersonDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "contact"));
        Proxy<Collection<InvoiceDomain>, Integer> invoiceProxy =
                new Proxy<>(guestDomain.getContactId(),
                        key -> InvoiceDomainBuilder.getLazyInstance().
                                lazyLoadCollection(key, "contact")
                );

        guestDomain.setContractingPartyDomainBuilderProxy(contractingPartyProxy);
        guestDomain.setPersonDomainBuilderProxy(personProxy);
        guestDomain.setInvoiceDomainBuilderProxy(invoiceProxy);

        mapAllCollections(mapping);
        return guestDomain;
    }

    @Override
    protected ContactEntity mapDomainToEntity(GuestDomain domain) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ContactEntity contactEntity = modelMapper.map(domain, ContactEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();

        put(ContactEntity.class, domain::getContactNotesByContactId, contactEntity::setContactNotesByContactId, mapping);
        put(CreditCardEntity.class, domain::getCreditCardsByContactId, contactEntity::setCreditCardsByContactId, mapping);
        put(ContractingPartyEntity.class, domain::getContractingPartiesByContactId,
                contactEntity::setContractingPartiesByContactId, mapping);
        put(PersonEntity.class, domain::getPeopleByContactId, contactEntity::setPeopleByContactId, mapping);
        put(InvoiceEntity.class, domain::getInvoicesByContactId, contactEntity::setInvoicesByContactId, mapping);

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

    @Override
    public GuestDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<GuestDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new GuestDomainBuilder(ContactDao::registerAtDao).
                loadByForeignKey(ContactEntity.class, key, referencedColumn);
    }
}
