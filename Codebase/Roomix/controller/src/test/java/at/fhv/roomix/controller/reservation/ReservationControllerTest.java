package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.SessionMock;
import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.GetFault;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.model.CategoryDataPojo;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.ReservationUnitPojo;
import at.fhv.roomix.controller.model.RoomCategoryPojo;
import at.fhv.roomix.controller.session.SessionControllerFactory;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.session.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ${FILE_NAME}
 * 07/06/2018 OliverHeil
 * <p>
 * Enter Description here
 */
class ReservationControllerTest {
    private static long sessionId;


    @BeforeAll
    static void init(){
        sessionId = 1234;
        SessionFactory.inject(new SessionMock());
    }

    @Test
    void calculateData_ContractingPartyNull() throws GetFault, SessionFaultException, ArgumentFaultException, ValidationFault {
        ReservationController controller = new ReservationController();

        Optional<RoomCategoryPojo> categoryPojo =
                controller.getAllCategory(sessionId).stream().findFirst();
        assertTrue(categoryPojo.isPresent());

        Collection<CategoryDataPojo> collection =
                controller.calculateData(sessionId, categoryPojo.get(), null, LocalDate.now(), LocalDate.now().plusDays(2));

        assertEquals(2, collection.size());
    }

    @Test
    void calculateData_ContractingPartyNew() throws GetFault, SessionFaultException, ArgumentFaultException, ValidationFault {
        ReservationController controller = new ReservationController();

        Optional<RoomCategoryPojo> categoryPojo =
                controller.getAllCategory(sessionId).stream().findFirst();
        assertTrue(categoryPojo.isPresent());

        ContactPojo pojo = new ContactPojo();
        pojo.setContactId(0);

        Collection<CategoryDataPojo> collection =
                controller.calculateData(sessionId, categoryPojo.get(), pojo, LocalDate.now(), LocalDate.now().plusDays(2));

        assertEquals(2, collection.size());
    }
}