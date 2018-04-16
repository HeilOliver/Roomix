package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.GuestDomainBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ReservationControllerTest
 * 08.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ReservationControllerTest {
    private static GuestDomainBuilderMock mock;

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

        assertThrows(ArgumentFaultException.class, () -> controller.updateContact(123L, null));

        ContactPojo pojo = new ContactPojo();
        pojo.setFname("Max");
        pojo.setLname("Mustermann");
        pojo.setCountry("Germany");
        pojo.setPhoneNumber("+4312132132132");
        pojo.setEmail("Some@some.com");
        pojo.setPlace("Dornbirn");
        pojo.setPostcode("6850");
        pojo.setStreet("SomeStreet 4");

        assertThrows(ValidationFault.class, () -> controller.updateContact(123L, pojo));
        pojo.setHouseNumber("123");

        controller.updateContact(110L, pojo);
        assertTrue(mock.getNewContactBool());

    }

    @Test
    void getAllContactsValid() throws SessionFaultException {
        ReservationController controller = new ReservationController();

        Collection gettAllTest = new HashSet();
        gettAllTest = controller.getAllContacts(123L);
        assertTrue(mock.isGetAllBool());
    }

    @Test
    void getSearchedContactsValid() throws SessionFaultException {
        ReservationController controller = new ReservationController();

        Collection gettAllTest = new HashSet();
        gettAllTest = controller.getSearchedContacts(123L, "test");
        assertTrue(mock.isGetAllBool());
    }

}