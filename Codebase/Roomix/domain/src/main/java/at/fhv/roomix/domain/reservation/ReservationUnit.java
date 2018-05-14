package at.fhv.roomix.domain.reservation;

import at.fhv.roomix.domain.common.Proxy;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.domain.room.RoomCategory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * ReservationUnit
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnit {
    private int id;
    private RoomCategory category;
    private LocalTime arrivalTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private boolean isCanceled;
    private Collection<Arrangement> arrangements = new HashSet<>();
    private Proxy<Reservation> reservation;

    public Collection<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(Collection<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public Reservation getReservation() {
        if (reservation == null) return null;
        return reservation.get();
    }

    //region GetterSetter
    public HashMap<LocalDate, Room> getAssignedRooms() {
        return assignedRooms;
    }

    public UnitStatus getStatus() {
        return status;
    }

    public void setStatus(UnitStatus status) {
        this.status = status;
    }

    public void setReservation(Proxy<Reservation> reservation) {
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }
    //endregion

    private UnitStatus status;
    private Collection<Person> guests = new HashSet<>(); // May her Proxy Collection
    private HashMap<LocalDate, Room> assignedRooms = new HashMap<>();

    public boolean canCheckedIn() {
        if (status != UnitStatus.NEW) return false;
        if (assignedRooms == null || assignedRooms.isEmpty()) return false;
        if (!assignedRooms.containsKey(startDate)) return false;

        Duration days = Duration.ofDays(ChronoUnit.DAYS.between(startDate, endDate));
        long neededDays = days.toDays();
        if (assignedRooms.size() != neededDays) return false;

        for (Room room : assignedRooms.values()) {
            if (room.getRoomStatus() != Room.RoomStatus.OUT_OF_ORDER) continue;
            return false;
        }

        return true;
    }

    public boolean isUntouched() {
        return status == UnitStatus.NEW;
    }

    public void addGuest(Person person) {
        guests.add(person);
    }

    public Collection<Person> getGuests() {
        return guests;
    }

    public boolean hasRoom() {
        return assignedRooms != null;
    }

    public void checkIn() {
        if (!canCheckedIn()) throw new IllegalStateException("Unit cant be CheckedIn");

        // Add this to an HashCollection to Checkin a room only once
        HashSet<Room> rooms = new HashSet<>(assignedRooms.values());
        for (Room room : rooms) {
            room.checkIn();
        }

        status = UnitStatus.CHECKED_IN;
    }

    public boolean isEditAble() {
        return status != UnitStatus.NEW;
    }

    public enum UnitStatus {
        NEW,
        CANCELED,
        CHECKED_IN,
        CHECKED_OUT
    }
}
