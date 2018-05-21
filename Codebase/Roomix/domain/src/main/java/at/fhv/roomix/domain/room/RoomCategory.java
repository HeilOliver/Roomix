package at.fhv.roomix.domain.room;

import at.fhv.roomix.domain.guest.contractingparty.Agreement;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.guest.contractingparty.TravelAgency;
import at.fhv.roomix.domain.payment.RoomPrice;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.stay.CategoryStatus;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Roomix
 * at.fhv.roomix.domain.room
 * RoomCategory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class RoomCategory  {
    private int id;
    private String description;
    private HashSet<Room> rooms = new HashSet<>();
    private PriceFinder priceFinder;
    private AgreementFinder agreementFinder;

    public void setUnitsLoader(ReservationUnitLoader unitsLoader) {
        this.unitsLoader = unitsLoader;
    }

    public void setPriceFinder(PriceFinder priceFinder) {
        this.priceFinder = priceFinder;
    }

    public void setAgreementFinder(AgreementFinder agreementFinder) {
        this.agreementFinder = agreementFinder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomsCount() {
        return rooms.size();
    }

    public HashSet<Room> getRooms() {
        return rooms;
    }

    private ReservationUnitLoader unitsLoader;

    public int calculatePrice(ContractingParty party, LocalDate statusDate) {
        if (statusDate == null) throw new IllegalArgumentException();

        RoomPrice price = priceFinder.getPrice(statusDate, this);
        int calcPrice = price.getListPrice();

        Agreement agreement;
        if (party != null) {
            agreement = agreementFinder.getAgreement(statusDate, this, party);
            int discount = agreement.getDiscount();
            calcPrice -= discount;
        }
        return calcPrice;
    }

    public CategoryStatus calculateStatus(ContractingParty party, LocalDate statusDate) {
        int unconfirmed = 0, occupied = 0, quota = 0;
        Collection<ReservationUnit> units = unitsLoader.getUnitsForDate(statusDate, this);

        if (party != null && party.getType() == ContractingParty.ContractingPartyType.TRAVEL_AGENT) {
            quota = ((TravelAgency)party).getQuota(statusDate, this);
        } else {
            party = null;
        }

        for (ReservationUnit unit : units) {
            if (unit.isCanceled()) continue;
            if (unit.getReservation().getContractingParty().equals(party)) {
                quota -= 1;
                continue;
            }
            if (unit.getStatus() == ReservationUnit.UnitStatus.NEW) {
                unconfirmed++;
                continue;
            }
            occupied++;
        }
        int price = calculatePrice(party, statusDate);
        return new CategoryStatus(statusDate, this, unconfirmed, occupied, quota, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCategory that = (RoomCategory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @FunctionalInterface
    public interface ReservationUnitLoader {
        Collection<ReservationUnit> getUnitsForDate(LocalDate date, RoomCategory roomCategory);
    }


    @FunctionalInterface
    public interface PriceFinder {
        RoomPrice getPrice(LocalDate date, RoomCategory roomCategory);
    }

    @FunctionalInterface
    public interface AgreementFinder {
        Agreement getAgreement(LocalDate date, RoomCategory roomCategory, ContractingParty party);
    }
}
