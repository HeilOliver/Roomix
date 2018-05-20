package at.fhv.roomix.domain.reservation;

import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.implement.IReservation;
import at.fhv.roomix.domain.implement.IReservationUnit;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * Reservation
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Reservation {
    private int id;
    private PaymentType paymentType;
    private ContractingParty contractingParty;
    private ReservationOption option;
    private Status status;
    private String comment;

    private Collection<Person> guests = new HashSet<>();
    private Collection<ReservationUnit> units = new HashSet<>();

    public Reservation() {
        status = Status.NEW;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReservationOption getOption() {
        return option;
    }

    public void setOption(ReservationOption option) {
        this.option = option;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public ContractingParty getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(ContractingParty contractingParty) {
        this.contractingParty = contractingParty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Collection<Person> getGuests() {
        return guests;
    }

    public void setGuests(Collection<Person> guests) {
        this.guests = guests;
    }

    public Collection<ReservationUnit> getUnits() {
        return units;
    }

    public void setUnits(Collection<ReservationUnit> units) {
        this.units = units;
    }

    public void calculate() {
        units.forEach(u -> u.calculatePrice());
    }

    public enum Status {
        NEW,
        PAYED,
        PARTLY_FULFILLED,
        FULFILLED
    }
}
