package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Season", schema = "roomix", catalog = "")
public class SeasonEntity {
    private int seasonId;
    private String description;
    private int additionalCharge;
    private Date startDate;
    private Date endDate;
    private Collection<RoomcategorypriceEntity> roomcategorypricesBySeasonId;

    @Id
    @Column(name = "SeasonID")
    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
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

    @Basic
    @Column(name = "StartDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "EndDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonEntity that = (SeasonEntity) o;
        return seasonId == that.seasonId &&
                additionalCharge == that.additionalCharge &&
                Objects.equals(description, that.description) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(seasonId, description, additionalCharge, startDate, endDate);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "seasonBySeason")
    public Collection<RoomcategorypriceEntity> getRoomcategorypricesBySeasonId() {
        return roomcategorypricesBySeasonId;
    }

    public void setRoomcategorypricesBySeasonId(Collection<RoomcategorypriceEntity> roomcategorypricesBySeasonId) {
        this.roomcategorypricesBySeasonId = roomcategorypricesBySeasonId;
    }
}
