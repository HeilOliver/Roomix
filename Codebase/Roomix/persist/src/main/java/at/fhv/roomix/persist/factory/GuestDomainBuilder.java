package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.persist.AbstractDao;
import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.PersistLoadException;
import at.fhv.roomix.persist.PersistSaveException;
import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persist.model.ContractingpartyEntity;
import at.fhv.roomix.persist.model.CreditcardEntity;
import at.fhv.roomix.persist.model.PersonEntity;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.concurrent.Callable;

public class GuestDomainBuilder extends AbstractDomainBuilder<GuestDomain, ContactEntity> {

    private GuestDomainBuilder(ICallable registerAtDAO){
        registerAtDAO.call();
    }

    public GuestDomainBuilder(){

    }


    @Override
    protected GuestDomain mapEntityToDomain(ContactEntity contactEntity){

        ModelMapper modelMapper = new ModelMapper();
        GuestDomain guestDomain = modelMapper.map(contactEntity, GuestDomain.class);

        /* Mapping of all collection entities to domain objects */
        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping =  new LinkedHashMap<>();

        put(ContactNoteDomain.class, contactEntity::getContactnotesByContactId,
                                     guestDomain::setContactNotes, mapping);
        put(CreditCardDomain.class, contactEntity::getCreditcardsByContactId,
                                     guestDomain::setCreditCards, mapping);
        put(ContractingPartyDomain.class, contactEntity::getContractingpartiesByContactId,
                                     guestDomain::setContractingPartys, mapping);
        put(PersonDomain.class, contactEntity::getPeopleByContactId,
                                     guestDomain::setPersonDomains, mapping);

        mapAllCollections(mapping);
        return guestDomain;
    }

    @Override
    protected ContactEntity mapDomainToEntity(GuestDomain domain) {

        ModelMapper modelMapper = new ModelMapper();
        ContactEntity contactEntity = modelMapper.map(domain, ContactEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping =  new LinkedHashMap<>();

        put(ContactEntity.class, domain::getContactNotes, contactEntity::setContactnotesByContactId, mapping);
        put(CreditcardEntity.class, domain::getCreditCards, contactEntity::setCreditcardsByContactId, mapping);
        put(ContractingpartyEntity.class, domain::getContractingPartys,
                contactEntity::setContractingpartiesByContactId, mapping);
        put(PersonEntity.class, domain::getPersonDomains, contactEntity::setPeopleByContactId, mapping);

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
