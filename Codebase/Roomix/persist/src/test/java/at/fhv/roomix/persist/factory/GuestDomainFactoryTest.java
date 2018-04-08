package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.persist.ContactDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestDomainFactoryTest {

    @Test
    void testGetInstanceById(){
        int testID = 1;
        GuestDomain testVarGuestDomain = new GuestDomainBuilder().get(testID);
        assertEquals(testVarGuestDomain.getContactId(), testID);


        GuestDomain newGuest = new GuestDomain();
        newGuest.setFname("Robert");
        newGuest.setLname("Schmitzer");
        newGuest.setPhoneNumber("0043 663 06015172");
        newGuest.setStreet("Sägerstr.");
        newGuest.setHouseNumber(59);
        newGuest.setPlace("Dornbirn");
        newGuest.setPostcode("6850");
        newGuest.setCountry("AT");
        newGuest.setEmail("rober.schmizter");
        newGuest.setActive((byte) 1);

        GuestDomainBuilder builder = new GuestDomainBuilder();
        builder.set(newGuest);
        /*
        List<GuestDomain> guestDomains = new GuestDomainBuilder(ContactDao::registerAtDao).getAll();
        guestDomains.forEach(guestDomain -> {
            guestDomain.getContactNotes().forEach(
                    contactNoteDomain -> {
                        System.out.println(contactNoteDomain.getNoteContent());
                    }
            );
        }); */
    }
}
