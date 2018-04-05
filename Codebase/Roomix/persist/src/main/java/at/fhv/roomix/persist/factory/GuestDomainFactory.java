package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.persist.model.ContactEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

public class GuestDomainFactory {

    private GuestDomainFactory(){

    }

    public static void main(String[] args){
        ModelMapper modelMapper = new ModelMapper();
        GuestDomain guestDomain = modelMapper.map(new ContactEntity(), GuestDomain.class);
    }

    public static GuestDomain getInstanceByID(int id){
        return null;
    }

    public static List<GuestDomain> searchAll(){
        return null;
    }

    public static void saveOrUpdateInstance(GuestDomain guestDomain){

    }

}
