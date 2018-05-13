package at.fhv.roomix.persist.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "RoomCategory", schema = "Roomix", catalog = "")
public class RoomCategoryEntity {
    private int roomCategoryId;
    private String categoryDescription;

    private Collection<PartnerAgreementEntity> partnerAgreementsByRoomCategoryId;
    private Collection<ReservationUnitEntity> reservationUnitsByRoomCategoryId;
    private Collection<RoomEntity> rooms;
    private Collection<RoomCategoryPriceEntity> roomCategoryPrices;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        RoomCategoryEntity that = (RoomCategoryEntity) o;
        return roomCategoryId == that.roomCategoryId &&
                Objects.equals(categoryDescription, that.categoryDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomCategoryId, categoryDescription);
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategoryByRoomCategory")
    public Collection<PartnerAgreementEntity> getPartnerAgreementsByRoomCategoryId() {
        return partnerAgreementsByRoomCategoryId;
    }

    public void setPartnerAgreementsByRoomCategoryId(Collection<PartnerAgreementEntity> partnerAgreementsByRoomCategoryId) {
        this.partnerAgreementsByRoomCategoryId = partnerAgreementsByRoomCategoryId;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategory")
    public Collection<ReservationUnitEntity> getReservationUnitsByRoomCategoryId() {
        return reservationUnitsByRoomCategoryId;
    }

    public void setReservationUnitsByRoomCategoryId(Collection<ReservationUnitEntity> reservationUnitsByRoomCategoryId) {
        this.reservationUnitsByRoomCategoryId = reservationUnitsByRoomCategoryId;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategory")
    public Collection<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<RoomEntity> roomsByRoomCategoryId) {
        this.rooms = roomsByRoomCategoryId;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategory")
    public Collection<RoomCategoryPriceEntity> getRoomCategoryPrices() {
        return roomCategoryPrices;
    }

    public void setRoomCategoryPrices(Collection<RoomCategoryPriceEntity> priceEntities) {
        this.roomCategoryPrices = priceEntities;
    }
}
