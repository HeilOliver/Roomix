package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "RoomCategory", schema = "Roomix", catalog = "")
public class RoomCategoryEntity {
    private int roomCategoryId;
    private String categoryDescription;
    private Collection<PartnerAgreementEntity> partnerAgreementsByRoomCategoryId;
    private Collection<ReservationUnitEntity> reservationUnitsByRoomCategoryId;
    private Collection<RoomEntity> roomsByRoomCategoryId;
    private Collection<RoomCategoryPriceEntity> roomCategoryPricesByRoomCategoryId;

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
        RoomCategoryEntity that = (RoomCategoryEntity) o;
        return roomCategoryId == that.roomCategoryId &&
                Objects.equals(categoryDescription, that.categoryDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomCategoryId, categoryDescription);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategoryByRoomCategory")
    public Collection<PartnerAgreementEntity> getPartnerAgreementsByRoomCategoryId() {
        return partnerAgreementsByRoomCategoryId;
    }

    public void setPartnerAgreementsByRoomCategoryId(Collection<PartnerAgreementEntity> partnerAgreementsByRoomCategoryId) {
        this.partnerAgreementsByRoomCategoryId = partnerAgreementsByRoomCategoryId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategoryByRoomCategory")
    public Collection<ReservationUnitEntity> getReservationUnitsByRoomCategoryId() {
        return reservationUnitsByRoomCategoryId;
    }

    public void setReservationUnitsByRoomCategoryId(Collection<ReservationUnitEntity> reservationUnitsByRoomCategoryId) {
        this.reservationUnitsByRoomCategoryId = reservationUnitsByRoomCategoryId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategoryByRoomCategory")
    public Collection<RoomEntity> getRoomsByRoomCategoryId() {
        return roomsByRoomCategoryId;
    }

    public void setRoomsByRoomCategoryId(Collection<RoomEntity> roomsByRoomCategoryId) {
        this.roomsByRoomCategoryId = roomsByRoomCategoryId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "roomCategoryByRoomCategory")
    public Collection<RoomCategoryPriceEntity> getRoomCategoryPricesByRoomCategoryId() {
        return roomCategoryPricesByRoomCategoryId;
    }

    public void setRoomCategoryPricesByRoomCategoryId(Collection<RoomCategoryPriceEntity> roomCategoryPricesByRoomCategoryId) {
        this.roomCategoryPricesByRoomCategoryId = roomCategoryPricesByRoomCategoryId;
    }
}
