package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.persist.ContactDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestDomainFactoryTest {

    @Test
    void testGetInstanceById(){
        int testID = 1;
        GuestDomain testVarGuestDomain = new GuestDomainBuilder(ContactDao::registerAtDao).getInstanceByID(testID);
        assertEquals(testVarGuestDomain.getContactId(), testID);
    }
}
