package at.fhv.roomix.controller.reservation.model;

import at.fhv.roomix.controller.contact.model.ContactPojo;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ReservationPojo
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationPojo {

    private int id;
    @NotNull(message = "comment cannot be null")
    private CommentPojo comment;
    @NotNull(message = "contractingParty cannot be null")
    private ContactPojo contractingParty;
    @NotNull(message = "reservationUnit cannot be null")
    private Collection<ReservationUnitPojo> reservationUnit;

    private Collection<ReservationOptionPojo> reservationOption;
    @NotNull(message = "persons cannot be null")
    private Collection<ContactPojo> persons;

    public ReservationPojo() {
        reservationUnit = new HashSet<>();
        persons = new HashSet<>();
        reservationOption = new HashSet<>();
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

    public Collection<ReservationUnitPojo> getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(Collection<ReservationUnitPojo> reservationUnit) {
        this.reservationUnit = reservationUnit;
    }

    public Collection<ContactPojo> getPersons() {
        return persons;
    }

    public void setPersons(Collection<ContactPojo> persons) {
        this.persons = persons;
    }

    public CommentPojo getComment() {
        return comment;
    }

    public void setComment(CommentPojo comment) {
        this.comment = comment;
    }

    public Collection<ReservationOptionPojo> getReservationOption() {
        return reservationOption;
    }

    public void setReservationOption(Collection<ReservationOptionPojo> reservationOption) {
        this.reservationOption = reservationOption;
    }
}
