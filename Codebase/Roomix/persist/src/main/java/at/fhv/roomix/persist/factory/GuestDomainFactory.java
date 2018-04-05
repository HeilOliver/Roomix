package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.persist.ContactDao;
import at.fhv.roomix.persist.PersistLoadException;
import at.fhv.roomix.persist.model.ContactEntity;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;

public class GuestDomainFactory {

    private static Logger logger = Logger.getLogger(Logger.class);

    public static void main(String[] args){
        GuestDomain testVarGuestDomain = GuestDomainFactory.getInstanceByID(1);
        System.out.println(testVarGuestDomain.getContactId());
    }

    private GuestDomainFactory(){

    }

    public static GuestDomain getInstanceByID(int id){
        ModelMapper modelMapper = new ModelMapper();
        ContactDao contactDao = ContactDao.getInstance();
        ContactEntity contactEntity = null;
        GuestDomain guestDomain = null;
        try {
            contactEntity = contactDao.load(id);
        } catch (PersistLoadException e) {
            logger.info(e.getMessage());
        }
        if (contactEntity != null) {
            guestDomain = modelMapper.map(contactEntity, GuestDomain.class);
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
