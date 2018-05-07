package at.fhv.roomix.domain.payment;

import at.fhv.roomix.domain.guest.contractingparty.Agreement;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.room.RoomCategory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.domain.price
 * PriceCalculator
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PriceCalculator {
    private final Collection<Agreement> agreements;
    private Collection<SeasonPrice> prices;

    public PriceCalculator(Collection<Agreement> agreements, Collection<SeasonPrice> prices) {
        this.agreements = agreements;
        this.prices = prices;
    }

    public Collection<DayPrice> getPriceFor(ReservationUnit unit) {
        RoomCategory category = unit.getCategory();
        HashSet<DayPrice> result = new HashSet<>();

        int dayCount = 0;
        LocalDate currDate = unit.getStartDate().plusDays(dayCount);
        do {
            LocalDate finalCurrDate = currDate;

            Set<Agreement> currAgreements = agreements.stream()
                    .filter((a) -> a.getCategory().equals(category))
                    .filter((a) -> finalCurrDate.isAfter(a.getStartDate()) || finalCurrDate.isEqual(a.getStartDate()))
                    .filter((a) -> finalCurrDate.isBefore(a.getExpiringDate()) || finalCurrDate.isEqual(a.getExpiringDate()))
                    .collect(Collectors.toSet());

            Optional<SeasonPrice> first = prices.stream()
                    .filter((u) -> u.getCategory().equals(category))
                    .filter((u) -> finalCurrDate.isAfter(u.getFrom()) || finalCurrDate.isEqual(u.getFrom()))
                    .filter((u) -> finalCurrDate.isBefore(u.getTill()) || finalCurrDate.isEqual(u.getTill()))
                    .findFirst();

            if (!first.isPresent()) throw new IllegalStateException("No Price is found");

            SeasonPrice seasonPrice = first.get();
            Optional<Agreement> agreement = currAgreements.stream().min(Comparator.comparingInt(Agreement::getDiscount));

            int price = seasonPrice.getListPrice();
            if (agreement.isPresent())
                price -= agreement.get().getDiscount();

            result.add(new DayPrice(currDate, price));

            currDate = unit.getStartDate().plusDays(++dayCount);
        } while (!currDate.isAfter(unit.getEndDate()));

        return result;
    }
}
