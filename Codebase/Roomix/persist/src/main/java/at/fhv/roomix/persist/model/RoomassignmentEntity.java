package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roomassignment", schema = "roomix", catalog = "")
public class RoomassignmentEntity {
    private int roomAssignmentId;
    private Date arrivalDate;
    private Date departureDate;
    private int room;
    private int reservationUnit;
    private Collection<PersonroomassignmentEntity> personroomassignmentsByRoomAssignmentId;
    private RoomEntity roomByRoom;
    private ReservationunitEntity reservationunitByReservationUnit;

    @Id
    @Column(name = "RoomAssignmentID")
    public int getRoomAssignmentId() {
        return roomAssignmentId;
    }

    public void setRoomAssignmentId(int roomAssignmentId) {
        this.roomAssignmentId = roomAssignmentId;
    }

    @Basic
    @Column(name = "ArrivalDate")
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Basic
    @Column(name = "DepartureDate")
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Basic
    @Column(name = "Room")
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Basic
    @Column(name = "ReservationUnit")
    public int getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(int reservationUnit) {
        this.reservationUnit = reservationUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomassignmentEntity that = (RoomassignmentEntity) o;
        return roomAssignmentId == that.roomAssignmentId &&
                room == that.room &&
                reservationUnit == that.reservationUnit &&
                Objects.equals(arrivalDate, that.arrivalDate) &&
                Objects.equals(departureDate, that.departureDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomAssignmentId, arrivalDate, departureDate, room, reservationUnit);
    }

    @OneToMany(mappedBy = "roomassignmentByRoomAssignment")
    public Collection<PersonroomassignmentEntity> getPersonroomassignmentsByRoomAssignmentId() {
        return personroomassignmentsByRoomAssignmentId;
    }

    public void setPersonroomassignmentsByRoomAssignmentId(Collection<PersonroomassignmentEntity> personroomassignmentsByRoomAssignmentId) {
        this.personroomassignmentsByRoomAssignmentId = personroomassignmentsByRoomAssignmentId;
    }

    @ManyToOne
    @JoinColumn(name = "Room", referencedColumnName = "RoomID", nullable = false)
    public RoomEntity getRoomByRoom() {
        return roomByRoom;
    }

    public void setRoomByRoom(RoomEntity roomByRoom) {
        this.roomByRoom = roomByRoom;
    }

    @ManyToOne
    @JoinColumn(name = "ReservationUnit", referencedColumnName = "ReservationUnitID", nullable = false)
    public ReservationunitEntity getReservationunitByReservationUnit() {
        return reservationunitByReservationUnit;
    }

    public void setReservationunitByReservationUnit(ReservationunitEntity reservationunitByReservationUnit) {
        this.reservationunitByReservationUnit = reservationunitByReservationUnit;
    }
}
