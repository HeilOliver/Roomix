package at.fhv.roomix.controller.model;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.implement.reservation.contact
 * ReservationPojo
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationPojo {
    private int id;
    private String reservationComment;

    @NotNull(message = "contractingParty cannot be null")
    private ContactPojo contractingParty;

    @NotNull(message = "units cannot be null")
    private Collection<ReservationUnitPojo> units;

    private ReservationOptionPojo option;

    @NotNull(message = "persons cannot be null")
    private Collection<PersonPojo> persons;

    @NotNull(message = "paymentType cannot be null")
    private PaymentTypePojo paymentType;

    public ReservationPojo() {
        units = new HashSet<>();
        persons = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactPojo getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(ContactPojo contractingParty) {
        this.contractingParty = contractingParty;
    }

    public Collection<ReservationUnitPojo> getUnits() {
        return units;
    }

    public void setUnits(Collection<ReservationUnitPojo> units) {
        this.units = units;
    }

    public ReservationOptionPojo getOption() {
        return option;
    }

    public void setOption(ReservationOptionPojo option) {
        this.option = option;
    }

    public Collection<PersonPojo> getPersons() {
        return persons;
    }

    public void setPersons(Collection<PersonPojo> persons) {
        this.persons = persons;
    }

    public PaymentTypePojo getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypePojo paymentType) {
        this.paymentType = paymentType;
    }

    public String getReservationComment() {
        return reservationComment;
    }

    public void setReservationComment(String reservationComment) {
        this.reservationComment = reservationComment;
    }
}
