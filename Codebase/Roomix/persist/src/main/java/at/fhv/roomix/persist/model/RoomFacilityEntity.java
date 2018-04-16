package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "RoomFacility", schema = "Roomix", catalog = "")
@IdClass(RoomFacilityEntityPK.class)
public class RoomFacilityEntity {
    private int room;
    private int facility;
    private int amount;
    private RoomEntity roomByRoom;
    private FacilityEntity facilityByFacility;

    @Id
    @Column(name = "Room", insertable = false, updatable = false)
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Id
    @Column(name = "Facility", insertable = false, updatable = false)
    public int getFacility() {
        return facility;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    @Basic
    @Column(name = "Amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomFacilityEntity that = (RoomFacilityEntity) o;
        return room == that.room &&
                facility == that.facility &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {

        return Objects.hash(room, facility, amount);
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
    @JoinColumn(name = "Facility", referencedColumnName = "FacilityID", nullable = false)
    public FacilityEntity getFacilityByFacility() {
        return facilityByFacility;
    }

    public void setFacilityByFacility(FacilityEntity facilityByFacility) {
        this.facilityByFacility = facilityByFacility;
    }
}
