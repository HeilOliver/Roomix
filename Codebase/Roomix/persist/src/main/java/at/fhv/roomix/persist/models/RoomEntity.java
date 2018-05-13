package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Room", schema = "Roomix", catalog = "")
public class RoomEntity {
    private int roomId;
    private String status;
    private RoomCategoryEntity roomCategory;
    private Collection<RoomAssignmentEntity> roomAssignmentsByRoomId;
    private Collection<RoomFacilityEntity> roomFacilitiesByRoomId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomID")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, status);
    }

    @ManyToOne
    @JoinColumn(name = "RoomCategory", referencedColumnName = "RoomCategoryID")
    public RoomCategoryEntity getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryEntity roomCategoryByRoomCategory) {
        this.roomCategory = roomCategoryByRoomCategory;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "room")
    public Collection<RoomAssignmentEntity> getRoomAssignmentsByRoomId() {
        return roomAssignmentsByRoomId;
    }

    public void setRoomAssignmentsByRoomId(Collection<RoomAssignmentEntity> roomAssignmentsByRoomId) {
        this.roomAssignmentsByRoomId = roomAssignmentsByRoomId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomByRoom")
    public Collection<RoomFacilityEntity> getRoomFacilitiesByRoomId() {
        return roomFacilitiesByRoomId;
    }

    public void setRoomFacilitiesByRoomId(Collection<RoomFacilityEntity> roomFacilitiesByRoomId) {
        this.roomFacilitiesByRoomId = roomFacilitiesByRoomId;
    }
}
