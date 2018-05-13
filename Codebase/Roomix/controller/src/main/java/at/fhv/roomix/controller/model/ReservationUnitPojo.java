package at.fhv.roomix.controller.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.contact
 * ReservationUnitPojo
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnitPojo {

    private int id;
    @NotNull(message = "roomCategory cannot be null")
    private RoomCategoryPojo roomCategory;
    @Range(min = 1)
    private int amount;
    @NotNull(message = "price cannot be null")
    private PricePojo price;

    private Collection<ArrangementPojo> arrangements;
    private Collection<RoomPojo> assignedRooms;

    public Collection<RoomPojo> getAssignedRooms() {
        return assignedRooms;
    }

    public void setAssignedRooms(Collection<RoomPojo> assignedRooms) {
        this.assignedRooms = assignedRooms;
    }

    private LocalTime arrivalTime;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationUnitPojo() {
        arrangements = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public Collection<ArrangementPojo> getArrangements() {
        return arrangements;
    }

    public void setArrangements(Collection<ArrangementPojo> arrangements) {
        this.arrangements = arrangements;
    }

    public RoomCategoryPojo getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryPojo roomCategory) {
        this.roomCategory = roomCategory;
    }

    public PricePojo getPrice() {
        return price;
    }

    public void setPrice(PricePojo price) {
        this.price = price;
    }
}
