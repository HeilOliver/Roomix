package at.fhv.roomix.persist.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Facility", schema = "Roomix", catalog = "")
public class FacilityEntity {
    private int facilityId;
    private String description;
    private int additionalCharge;
    private Collection<RoomFacilityEntity> roomFacilitiesByFacilityId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacilityID")
    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "AdditionalCharge")
    public int getAdditionalCharge() {
        return additionalCharge;
    }

    public void setAdditionalCharge(int additionalCharge) {
        this.additionalCharge = additionalCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityEntity that = (FacilityEntity) o;
        return facilityId == that.facilityId &&
                additionalCharge == that.additionalCharge &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(facilityId, description, additionalCharge);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "facilityByFacility")
    public Collection<RoomFacilityEntity> getRoomFacilitiesByFacilityId() {
        return roomFacilitiesByFacilityId;
    }

    public void setRoomFacilitiesByFacilityId(Collection<RoomFacilityEntity> roomFacilitiesByFacilityId) {
        this.roomFacilitiesByFacilityId = roomFacilitiesByFacilityId;
    }
}
