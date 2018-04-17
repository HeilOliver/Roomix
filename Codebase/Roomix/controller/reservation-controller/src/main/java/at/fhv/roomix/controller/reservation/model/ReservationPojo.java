package at.fhv.roomix.controller.reservation.model;

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
    private String comment;

    private ContactPojo contractingParty;
    private Collection<ReservationUnitPojo> units;
    private Collection<ContactPojo> persons;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
