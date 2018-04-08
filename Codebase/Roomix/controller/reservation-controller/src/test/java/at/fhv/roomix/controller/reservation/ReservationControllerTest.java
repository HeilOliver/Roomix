package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.GuestDomainBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationControllerTest
 * 08.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ReservationControllerTest {
    static GuestDomainBuilderMock mock;

    @BeforeAll
    static void init() {
        mock = new GuestDomainBuilderMock();

        GuestDomainBuilder.injectDependency(() -> mock);
        SessionDomainMock sessionDomain = new SessionDomainMock();
        SessionFactory.inject(sessionDomain);
    }

   @Test
   void newContact() throws SessionFaultException, ArgumentFaultException, ValidationFault {
       ReservationController controller = new ReservationController();

       assertThrows(ArgumentFaultException.class, () -> controller.newContact(123L, null));

       ContactPojo pojo = new ContactPojo();
       pojo.setFname("Max");
       pojo.setLname("Mustermann");
       pojo.setCountry("Germany");
       pojo.setPhoneNumber("+4312132132132");
       pojo.setEmail("Some@some.com");
       pojo.setPlace("Dornbirn");
       pojo.setPostcode("6850");
       pojo.setStreet("SomeStreet 4");

       assertThrows(ValidationFault.class, () -> controller.newContact(123L, pojo));
       pojo.setHouseNumber("123");

   }

   @Test
    void getAllContactsValid() {



   }

}