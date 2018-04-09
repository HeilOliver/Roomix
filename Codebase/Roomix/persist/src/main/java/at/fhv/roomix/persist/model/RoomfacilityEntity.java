package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roomfacility", schema = "roomix", catalog = "")
public class RoomfacilityEntity {
    private int roomFacilityId;
    private int room;
    private int facility;
    private RoomEntity roomByRoom;
    private FacilityEntity facilityByFacility;

    @Id
    @Column(name = "RoomFacilityID")
    public int getRoomFacilityId() {
        return roomFacilityId;
    }

    public void setRoomFacilityId(int roomFacilityId) {
        this.roomFacilityId = roomFacilityId;
    }

    @Basic
    @Column(name = "Room", insertable = false, updatable = false)
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Basic
    @Column(name = "Facility", insertable = false, updatable = false)
    public int getFacility() {
        return facility;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomfacilityEntity that = (RoomfacilityEntity) o;
        return roomFacilityId == that.roomFacilityId &&
                room == that.room &&
                facility == that.facility;
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomFacilityId, room, facility);
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
