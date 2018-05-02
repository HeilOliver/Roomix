package at.fhv.roomix.domain.stay;

import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.guest.room.RoomCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Roomix
 * at.fhv.roomix.domain.stay
 * CategoryFinder
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryFinder {
    private Collection<ReservationUnit> units;

    public List<CategoryStatus> calculateStatus(LocalDate startDate, LocalDate endDate, RoomCategory category) {
        if (!startDate.isBefore(endDate)) throw new IllegalArgumentException("End date is before start date");
        ArrayList<CategoryStatus> statuses = new ArrayList<>();

        int dayCount = 0;
        LocalDate currDate = startDate.plusDays(dayCount);
        do {
            LocalDate finalCurrDate = currDate;
            AtomicInteger unconfirmed = new AtomicInteger();
            AtomicInteger occupied = new AtomicInteger();

            units.stream()
                    .filter((u) -> finalCurrDate.isAfter(u.getStartDate()) || finalCurrDate.isEqual(u.getStartDate()))
                    .filter((u) -> finalCurrDate.isBefore(u.getEndDate())|| finalCurrDate.isEqual(u.getEndDate()))
                    .filter((u) -> !u.isCanceled())
                    .filter((u) -> u.getCategory().equals(category))
                    .forEach((u) -> {
                        if (u.getReservation().getStatus() == Reservation.Status.NEW) {
                            unconfirmed.incrementAndGet();
                        } else {
                            occupied.incrementAndGet();
                        }
                    });

            statuses.add(new CategoryStatus(finalCurrDate, category, unconfirmed.get(), occupied.get()));
            currDate = startDate.plusDays(++dayCount);
        } while (!currDate.isAfter(endDate));

        return statuses;
    }

    public void setUnits(Collection<ReservationUnit> units) {
        this.units = units;
    }
}
