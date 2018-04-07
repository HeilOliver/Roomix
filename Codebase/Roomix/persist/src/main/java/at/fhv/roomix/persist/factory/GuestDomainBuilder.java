package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.persist.AbstractDao;
import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.PersistLoadException;
import at.fhv.roomix.persist.model.ContactEntity;
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

    public static void main(String[] args){
        GuestDomain gd = new GuestDomainBuilder(ContactDao::registerAtDao).get(1);
        System.out.println(gd.getFname());
    }

    // TODO: remove
    public GuestDomain getInstanceByID(int id){
        /* Get contact Entity via Data Access Object */
        ContactDao contactDao = ContactDao.getInstance();
        ContactEntity contactEntity = null;
        try {
            contactEntity = contactDao.load(id);
        } catch (PersistLoadException e) {
            logger.info(e.getMessage());
        }

        /* Mapping from Entity to Domain Objects */
        GuestDomain guestDomain = null;
        if (contactEntity != null) {
            /* Basic variable mapping without referenced entities */
            guestDomain = mapEntityToDomain(contactEntity);

        } else {
            logger.warn("Null Entity fetched from DAO.");
        }
        return guestDomain;
    }

    // TODO: implement in AbstractDomainBuilder
    public List<GuestDomain> searchAll(){
        ContactDao contactDao = ContactDao.getInstance();
        List<ContactEntity> contactEntities = null;
        try {
            contactEntities = contactDao.loadAll();
        } catch (PersistLoadException e) {
            logger.info(e.getMessage());
        }
        List<GuestDomain> guestDomains = new LinkedList<>();
        for(ContactEntity contactEntity : contactEntities){
            guestDomains.add(mapEntityToDomain(contactEntity));
        }
        return guestDomains;
    }

    public static void saveOrUpdateInstance(GuestDomain guestDomain){

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
    protected GuestDomain get(int id) {
        return new GuestDomainBuilder(ContactDao::registerAtDao).getById(id, ContactEntity.class);
    }

    @Override
    protected List<GuestDomain> getAll() {
        return null;
    }

    @Override
    protected void set(GuestDomain domainObject) {

    }

}
