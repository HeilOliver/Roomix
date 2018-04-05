package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personroomassignment", schema = "roomix", catalog = "")
public class PersonroomassignmentEntity {
    private int personRoomAssignmentId;
    private int roomAssignment;
    private int person;
    private RoomassignmentEntity roomassignmentByRoomAssignment;
    private PersonEntity personByPerson;

    @Id
    @Column(name = "PersonRoomAssignmentID")
    public int getPersonRoomAssignmentId() {
        return personRoomAssignmentId;
    }

    public void setPersonRoomAssignmentId(int personRoomAssignmentId) {
        this.personRoomAssignmentId = personRoomAssignmentId;
    }

    @Basic
    @Column(name = "RoomAssignment", insertable=false, updatable=false)
    public int getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(int roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    @Basic
    @Column(name = "Person", insertable=false, updatable=false)
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
        PersonroomassignmentEntity that = (PersonroomassignmentEntity) o;
        return personRoomAssignmentId == that.personRoomAssignmentId &&
                roomAssignment == that.roomAssignment &&
                person == that.person;
    }

    @Override
    public int hashCode() {

        return Objects.hash(personRoomAssignmentId, roomAssignment, person);
    }

    @ManyToOne
    @JoinColumn(name = "RoomAssignment", referencedColumnName = "RoomAssignmentID", nullable = false)
    public RoomassignmentEntity getRoomassignmentByRoomAssignment() {
        return roomassignmentByRoomAssignment;
    }

    public void setRoomassignmentByRoomAssignment(RoomassignmentEntity roomassignmentByRoomAssignment) {
        this.roomassignmentByRoomAssignment = roomassignmentByRoomAssignment;
    }

    @ManyToOne
    @JoinColumn(name = "Person", referencedColumnName = "PersonID", nullable = false)
    public PersonEntity getPersonByPerson() {
        return personByPerson;
    }

    public void setPersonByPerson(PersonEntity personByPerson) {
        this.personByPerson = personByPerson;
    }
}
