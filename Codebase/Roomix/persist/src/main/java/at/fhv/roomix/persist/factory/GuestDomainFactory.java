package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.ContactNoteDomain;
import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.PersistLoadException;
import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persist.model.ContactnoteEntity;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GuestDomainFactory {

    private static Logger logger = Logger.getLogger(Logger.class);

    private GuestDomainFactory(){

    }

    public static void main(String[] args){
        GuestDomain gd = GuestDomainFactory.getInstanceByID(1);
        gd.getContactNote().forEach(contactNoteDomain -> System.out.println(contactNoteDomain.getNoteContent()));
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
        ModelMapper modelMapper = new ModelMapper();
        GuestDomain guestDomain = null;
        if (contactEntity != null) {

            ModelMapper contactNoteMapper = new ModelMapper();
            Collection<ContactnoteEntity> contactNoteEntites = contactEntity.getContactnotesByContactId();
            Collection<ContactNoteDomain> contactNoteDomains = new LinkedList<>();
            for(ContactnoteEntity contactNote : contactNoteEntites){
                ContactNoteDomain contactNoteDomain = contactNoteMapper.map(contactNote, ContactNoteDomain.class);
                contactNoteDomains.add(contactNoteDomain);
            }

            guestDomain = modelMapper.map(contactEntity, GuestDomain.class);
            guestDomain.setContactNote(contactNoteDomains);

        } else {
            logger.warn("Null Entity fetched from DAO.");
        }
        return guestDomain;
    }

    public static List<GuestDomain> searchAll(){
        return null;
    }

    public static void saveOrUpdateInstance(GuestDomain guestDomain){

    }

}
