package at.fhv.roomix.controller.model;

import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.controller.model.ReservationUnitPojo;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.controller.stay.model
 * CheckInPojo
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CheckInPojo {
    private ReservationUnitPojo unit;
    private Collection<PersonPojo> assignedPerson;

    public ReservationUnitPojo getUnit() {
        return unit;
    }

    public void setUnit(ReservationUnitPojo unit) {
        this.unit = unit;
    }

    public Collection<PersonPojo> getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(Collection<PersonPojo> assignedPerson) {
        this.assignedPerson = assignedPerson;
    }
}
