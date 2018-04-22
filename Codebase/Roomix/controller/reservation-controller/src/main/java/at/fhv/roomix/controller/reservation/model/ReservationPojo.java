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
    @NotNull(message = "units cannot be null")
    private Collection<ReservationUnitPojo> units;

    private Collection<ReservationOptionPojo> options;
    @NotNull(message = "persons cannot be null")
    private Collection<ContactPojo> persons;

    public ReservationPojo() {
        units = new HashSet<>();
        persons = new HashSet<>();
        options = new HashSet<>();
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

    public Collection<ReservationOptionPojo> getOptions() {
        return options;
    }

    public void setOptions(Collection<ReservationOptionPojo> options) {
        this.options = options;
    }
}
