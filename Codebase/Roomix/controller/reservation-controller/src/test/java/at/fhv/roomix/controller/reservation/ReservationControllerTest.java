package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.*;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.factory.ReservationDomainBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * ContactControllerTest
 * 08.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ReservationControllerTest {
    private static ReservationDomainBuilderMock mock;

    @BeforeAll
    static void init() {
        mock = new ReservationDomainBuilderMock();

        ReservationDomainBuilder.injectDependency(() -> mock);
        SessionDomainMock sessionDomain = new SessionDomainMock();
        SessionFactory.inject(sessionDomain);
    }

    @Test
    void updateReservation() throws SessionFaultException, ValidationFault, ArgumentFaultException {
        ReservationController controller = new ReservationController();

        assertThrows(ArgumentFaultException.class, () -> controller.updateReservation(123L, null));

        ReservationPojo resPojo = new ReservationPojo();
        resPojo.setId(123);
        resPojo.setComment(new CommentPojo());
        resPojo.setContractingParty(new ContactPojo());
        resPojo.setReservationOptionByReservationOption(new HashSet<>());
        resPojo.setPersonReservationsByReservationId(new HashSet<>());
        resPojo.setReservationUnitsByReservationId(new HashSet<>());

        controller.updateReservation(110L, resPojo);
        assertTrue(mock.getNewReservation());

    }

}