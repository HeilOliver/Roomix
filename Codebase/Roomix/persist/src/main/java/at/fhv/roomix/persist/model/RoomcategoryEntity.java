package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roomcategory", schema = "roomix", catalog = "")
public class RoomcategoryEntity {
    private int roomCategoryId;
    private String categoryDescription;
    private Collection<ReservationunitEntity> reservationunitsByRoomCategoryId;
    private Collection<RoomEntity> roomsByRoomCategoryId;
    private Collection<RoomcategorypriceEntity> roomcategorypricesByRoomCategoryId;

    @Id
    @Column(name = "RoomCategoryID")
    public int getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(int roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    @Basic
    @Column(name = "CategoryDescription")
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomcategoryEntity that = (RoomcategoryEntity) o;
        return roomCategoryId == that.roomCategoryId &&
                Objects.equals(categoryDescription, that.categoryDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomCategoryId, categoryDescription);
    }

    @OneToMany(mappedBy = "roomcategoryByRoomCategory")
    public Collection<ReservationunitEntity> getReservationunitsByRoomCategoryId() {
        return reservationunitsByRoomCategoryId;
    }

    public void setReservationunitsByRoomCategoryId(Collection<ReservationunitEntity> reservationunitsByRoomCategoryId) {
        this.reservationunitsByRoomCategoryId = reservationunitsByRoomCategoryId;
    }

    @OneToMany(mappedBy = "roomcategoryByRoomCategory")
    public Collection<RoomEntity> getRoomsByRoomCategoryId() {
        return roomsByRoomCategoryId;
    }

    public void setRoomsByRoomCategoryId(Collection<RoomEntity> roomsByRoomCategoryId) {
        this.roomsByRoomCategoryId = roomsByRoomCategoryId;
    }

    @OneToMany(mappedBy = "roomcategoryByRoomCategory")
    public Collection<RoomcategorypriceEntity> getRoomcategorypricesByRoomCategoryId() {
        return roomcategorypricesByRoomCategoryId;
    }

    public void setRoomcategorypricesByRoomCategoryId(Collection<RoomcategorypriceEntity> roomcategorypricesByRoomCategoryId) {
        this.roomcategorypricesByRoomCategoryId = roomcategorypricesByRoomCategoryId;
    }
}
