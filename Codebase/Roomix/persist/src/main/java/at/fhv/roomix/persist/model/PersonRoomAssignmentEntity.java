package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PersonRoomAssignment", schema = "Roomix", catalog = "")
@IdClass(PersonRoomAssignmentEntityPK.class)
public class PersonRoomAssignmentEntity {
    private int roomAssignment;
    private int person;
    private RoomAssignmentEntity roomAssignmentByRoomAssignment;
    private PersonEntity personByPerson;

    @Id
    @Column(name = "RoomAssignment", insertable = false, updatable = false)
    public int getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(int roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    @Id
    @Column(name = "Person", insertable = false, updatable = false)
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
        PersonRoomAssignmentEntity that = (PersonRoomAssignmentEntity) o;
        return roomAssignment == that.roomAssignment &&
                person == that.person;
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomAssignment, person);
    }

    @ManyToOne
    @JoinColumn(name = "RoomAssignment", referencedColumnName = "RoomAssignmentID", nullable = false, insertable = false, updatable = false)
    public RoomAssignmentEntity getRoomAssignmentByRoomAssignment() {
        return roomAssignmentByRoomAssignment;
    }

    public void setRoomAssignmentByRoomAssignment(RoomAssignmentEntity roomAssignmentByRoomAssignment) {
        this.roomAssignmentByRoomAssignment = roomAssignmentByRoomAssignment;
    }

    @ManyToOne
    @JoinColumn(name = "Person", referencedColumnName = "PersonID", nullable = false, insertable = false, updatable = false)
    public PersonEntity getPersonByPerson() {
        return personByPerson;
    }

    public void setPersonByPerson(PersonEntity personByPerson) {
        this.personByPerson = personByPerson;
    }
}
