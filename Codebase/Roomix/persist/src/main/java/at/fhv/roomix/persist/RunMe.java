package at.fhv.roomix.persist;

import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.reservation.*;
import at.fhv.roomix.domain.guest.contractingparty.Individual;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.domain.stay.CategoryFinder;
import at.fhv.roomix.domain.stay.CategoryStatus;
import at.fhv.roomix.persist.builder.accessbuilder.*;
import at.fhv.roomix.persist.dataaccess.dao.HibernateSessionController;
import at.fhv.roomix.persist.dataaccess.factory.EntityFactory;
import at.fhv.roomix.persist.exception.PersistException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * Roomix
 * at.fhv.roomix.persist
 * RunMe
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RunMe {

    public static void main(String[] args) throws PersistException {
        //loadAllReservations();
        //createReservationTest();
        getCategoryStatus();
        HibernateSessionController.disposeHibernate();
    }

    private static void getCategoryStatus() throws PersistException {
        CategoryFinder categoryFinder = new CategoryFinder();
        categoryFinder.setUnits(ReservationUnitBuilder.getAll());

        for (RoomCategory category : RoomCategoryBuilder.getRoomCategories()) {
            List<CategoryStatus> categoryStatuses
                    = categoryFinder.calculateStatus(LocalDate.now(), LocalDate.now().plusDays(5), category, null);
            categoryStatuses.forEach((s) -> System.out.print(String.format("%d/%d/%d - ",s.getFree(), s.getOccupied(), s.getUnconfirmed())));
            System.out.println(category.getDescription());
        }
    }

    private static void loadAllReservations() throws PersistException {
        Collection<Reservation> reservation = ReservationBuilder.getAll();
    }

    private static void createReservationTest() throws PersistException {
        Contact contact = ContactBuilder.getContact(2);
        PaymentType paymentType = PaymentTypeBuilder.getPaymentType(1);
        RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(1);
        Arrangement arrangement = ArrangementBuilder.getArrangement(1);
        Individual individual = ContractingPartyBuilder.getIndividual(contact.getId());

        Reservation reservation = new Reservation();
        reservation.setComment("Viel Wasser");
        reservation.setContractingParty(individual);
        reservation.getGuests().add(new Person(contact));
        reservation.getGuests().add(new Person("Sample", "Person"));
        reservation.setPaymentType(paymentType);

        ReservationUnit unit = new ReservationUnit();
        unit.setArrivalTime(LocalTime.NOON);
        unit.setStartDate(LocalDate.now().plusDays(2));
        unit.setEndDate(LocalDate.now().plusDays(5));
        unit.setCategory(roomCategory);
        unit.setPrice(4000);
        unit.getArrangements().add(arrangement);
        reservation.getUnits().add(unit);

        ReservationOption option = new ReservationOption();
        option.setDaysBeforeArrival(5);
        option.setDescription("Anzahlung");
        option.setPercentage(20);
        reservation.setOption(option);

        ReservationBuilder.update(reservation);

        EntityFactory.commitAll();

        System.out.println("End");
    }
}
