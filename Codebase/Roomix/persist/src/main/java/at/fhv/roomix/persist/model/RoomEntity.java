package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "room", schema = "roomix", catalog = "")
public class RoomEntity {
    private int roomId;
    private int roomCategory;
    private String status;
    private RoomcategoryEntity roomcategoryByRoomCategory;
    private Collection<RoomassignmentEntity> roomassignmentsByRoomId;
    private Collection<RoomfacilityEntity> roomfacilitiesByRoomId;

    @Id
    @Column(name = "RoomID")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "RoomCategory", insertable=false, updatable=false)
    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return roomId == that.roomId &&
                roomCategory == that.roomCategory &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomId, roomCategory, status);
    }

    @ManyToOne
    @JoinColumn(name = "RoomCategory", referencedColumnName = "RoomCategoryID", nullable = false)
    public RoomcategoryEntity getRoomcategoryByRoomCategory() {
        return roomcategoryByRoomCategory;
    }

    public void setRoomcategoryByRoomCategory(RoomcategoryEntity roomcategoryByRoomCategory) {
        this.roomcategoryByRoomCategory = roomcategoryByRoomCategory;
    }

    @OneToMany(mappedBy = "roomByRoom")
    public Collection<RoomassignmentEntity> getRoomassignmentsByRoomId() {
        return roomassignmentsByRoomId;
    }

    public void setRoomassignmentsByRoomId(Collection<RoomassignmentEntity> roomassignmentsByRoomId) {
        this.roomassignmentsByRoomId = roomassignmentsByRoomId;
    }

    @OneToMany(mappedBy = "roomByRoom")
    public Collection<RoomfacilityEntity> getRoomfacilitiesByRoomId() {
        return roomfacilitiesByRoomId;
    }

    public void setRoomfacilitiesByRoomId(Collection<RoomfacilityEntity> roomfacilitiesByRoomId) {
        this.roomfacilitiesByRoomId = roomfacilitiesByRoomId;
    }
}
