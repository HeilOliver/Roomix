package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.*;
import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.PersistLoadException;
import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persist.model.ContactnoteEntity;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GuestDomainFactory extends AbstractFactory{

    private static Logger logger = Logger.getLogger(Logger.class);

    private GuestDomainFactory(){

    }

    public static void main(String[] args){
        GuestDomain gd = GuestDomainFactory.getInstanceByID(1);
        gd.getPersonDomains().forEach(contactNoteDomain -> System.out.println(contactNoteDomain.getIsVip()));
    }

    public static GuestDomain getInstanceByID(int id){

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

    public static List<GuestDomain> searchAll(){
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

    /**
     *
     * @param contactEntity
     * @return
     */
    private static GuestDomain mapEntityToDomain(ContactEntity contactEntity){
        ModelMapper modelMapper = new ModelMapper();
        GuestDomain guestDomain = modelMapper.map(contactEntity, GuestDomain.class);
            /* Mapping of all collection entities to domain objects */
        Collection<ContactNoteDomain> contactNoteDomains =
                mapCollection(contactEntity.getContactnotesByContactId(), ContactNoteDomain.class);
        Collection<CreditCardDomain> creditCardDomains =
                mapCollection(contactEntity.getCreditcardsByContactId(), CreditCardDomain.class);
        Collection<ContractingPartyDomain> contractingPartyDomains =
                mapCollection(contactEntity.getContractingpartiesByContactId(), ContractingPartyDomain.class);
        Collection<PersonDomain> personDomains =
                mapCollection(contactEntity.getPeopleByContactId(), PersonDomain.class);

        guestDomain.setContractingPartys(contractingPartyDomains);
        guestDomain.setCreditCards(creditCardDomains);
        guestDomain.setContactNotes(contactNoteDomains);
        guestDomain.setPersonDomains(personDomains);
        return guestDomain;
    }

}
