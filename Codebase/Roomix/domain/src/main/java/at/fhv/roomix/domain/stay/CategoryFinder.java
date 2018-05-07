package at.fhv.roomix.domain.stay;

import at.fhv.roomix.domain.common.ILazyLoader;
import at.fhv.roomix.domain.common.ProxyLoadException;
import at.fhv.roomix.domain.guest.contractingparty.Agreement;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.room.RoomCategory;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    private ILazyLoader<Collection<Agreement>, ContractingParty> agreementSupplier;

    public List<CategoryStatus> calculateStatus(LocalDate startDate, LocalDate endDate, RoomCategory category, ContractingParty party) {
        if (!startDate.isBefore(endDate)) throw new IllegalArgumentException("End date is before start date");
        ArrayList<CategoryStatus> statuses = new ArrayList<>();

        Collection<Agreement> agreements = new HashSet<>();
        if (party != null && party.getId() != 0) {
            try {
                agreements = agreementSupplier.get(party);
                agreements = agreements.stream()
                        .filter((a) -> a.getCategory().equals(category))
                        .collect(Collectors.toSet());
            } catch (Exception e) {
                throw new ProxyLoadException(e.getMessage());
            }
        }

        int dayCount = 0;
        LocalDate currDate = startDate.plusDays(dayCount);
        do {
            LocalDate finalCurrDate = currDate;
            AtomicInteger unconfirmed = new AtomicInteger();
            AtomicInteger occupied = new AtomicInteger();
            AtomicInteger quota = new AtomicInteger();
            Set<Agreement> currAgreements = agreements.stream()
                    .filter((a) -> finalCurrDate.isAfter(a.getStartDate()) || finalCurrDate.isEqual(a.getStartDate()))
                    .filter((a) -> finalCurrDate.isBefore(a.getExpiringDate()) || finalCurrDate.isEqual(a.getExpiringDate()))
                    .collect(Collectors.toSet());
            currAgreements
                    .forEach((a) -> quota.addAndGet(a.getRoomCount()));

            units.stream()
                    .filter((u) -> finalCurrDate.isAfter(u.getStartDate()) || finalCurrDate.isEqual(u.getStartDate()))
                    .filter((u) -> finalCurrDate.isBefore(u.getEndDate()) || finalCurrDate.isEqual(u.getEndDate()))
                    .filter((u) -> !u.isCanceled())
                    .filter((u) -> u.getCategory().equals(category))
                    .forEach((u) -> {
                        if (u.getReservation().getStatus() == Reservation.Status.NEW) {
                            unconfirmed.incrementAndGet();
                        } else {
                            occupied.incrementAndGet();
                        }
                        if (u.getReservation().getContractingParty().equals(party))
                            quota.decrementAndGet();
                    });

            statuses.add(new CategoryStatus(finalCurrDate, category, unconfirmed.get(), occupied.get(), quota.get()));
            currDate = startDate.plusDays(++dayCount);
        } while (!currDate.isAfter(endDate));

        return statuses;
    }

    public void setUnits(Collection<ReservationUnit> units) {
        this.units = units;
    }

    public void setAgreementSupplier(ILazyLoader<Collection<Agreement>, ContractingParty> agreementSupplier) {
        this.agreementSupplier = agreementSupplier;
    }
}
