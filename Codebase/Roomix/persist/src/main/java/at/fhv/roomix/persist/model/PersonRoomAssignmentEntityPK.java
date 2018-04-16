package at.fhv.roomix.persist.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PersonRoomAssignmentEntityPK implements Serializable {
    private int roomAssignment;
    private int person;

    @Column(name = "RoomAssignment")
    @Id
    public int getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(int roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    @Column(name = "Person")
    @Id
    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonRoomAssignmentEntityPK that = (PersonRoomAssignmentEntityPK) o;
        return roomAssignment == that.roomAssignment &&
                person == that.person;
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomAssignment, person);
    }
}
