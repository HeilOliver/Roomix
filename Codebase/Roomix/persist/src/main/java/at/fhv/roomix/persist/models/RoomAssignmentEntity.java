package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "RoomAssignment", schema = "Roomix", catalog = "")
public class RoomAssignmentEntity {
    private int roomAssignmentId;
    private Date arrivalDate;
    private Date departureDate;
    private int room;
    private Collection<InvoicePositionEntity> invoicePositionsByRoomAssignmentId;
    private Collection<PersonEntity> persons;
    private RoomEntity roomByRoom;
    private ReservationUnitEntity reservationUnit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "Room", insertable = false, updatable = false)
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAssignmentEntity that = (RoomAssignmentEntity) o;
        return roomAssignmentId == that.roomAssignmentId &&
                room == that.room &&
                Objects.equals(arrivalDate, that.arrivalDate) &&
                Objects.equals(departureDate, that.departureDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomAssignmentId, arrivalDate, departureDate, room);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomAssignment")
    public Collection<InvoicePositionEntity> getInvoicePositionsByRoomAssignmentId() {
        return invoicePositionsByRoomAssignmentId;
    }

    public void setInvoicePositionsByRoomAssignmentId(Collection<InvoicePositionEntity> invoicePositionsByRoomAssignmentId) {
        this.invoicePositionsByRoomAssignmentId = invoicePositionsByRoomAssignmentId;
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
    @JoinColumn(name = "ReservationUnit")
    public ReservationUnitEntity getReservationUnit() {
        return reservationUnit;
    }

    public void setReservationUnit(ReservationUnitEntity reservationUnitByReservationUnit) {
        this.reservationUnit = reservationUnitByReservationUnit;
    }

    @ManyToMany(mappedBy = "roomAssignments")
    public Collection<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(Collection<PersonEntity> persons) {
        this.persons = persons;
    }
}
