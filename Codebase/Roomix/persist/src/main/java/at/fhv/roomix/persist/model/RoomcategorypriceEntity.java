package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "RoomCategoryPrice", schema = "roomix", catalog = "")
public class RoomcategorypriceEntity {
    private int roomCategoryPriceId;
    private int roomCategory;
    private int season;
    private int listPrice;
    private int acquisitionPrice;
    private int minimumPrice;
    private Integer dayPrice;
    private RoomcategoryEntity roomcategoryByRoomCategory;
    private SeasonEntity seasonBySeason;

    @Id
    @Column(name = "RoomCategoryPriceID")
    public int getRoomCategoryPriceId() {
        return roomCategoryPriceId;
    }

    public void setRoomCategoryPriceId(int roomCategoryPriceId) {
        this.roomCategoryPriceId = roomCategoryPriceId;
    }

    @Basic
    @Column(name = "RoomCategory", insertable = false, updatable = false)
    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Basic
    @Column(name = "Season", insertable = false, updatable = false)
    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    @Basic
    @Column(name = "ListPrice")
    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    @Basic
    @Column(name = "AcquisitionPrice")
    public int getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(int acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    @Basic
    @Column(name = "MinimumPrice")
    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    @Basic
    @Column(name = "DayPrice")
    public Integer getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Integer dayPrice) {
        this.dayPrice = dayPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomcategorypriceEntity that = (RoomcategorypriceEntity) o;
        return roomCategoryPriceId == that.roomCategoryPriceId &&
                roomCategory == that.roomCategory &&
                season == that.season &&
                listPrice == that.listPrice &&
                acquisitionPrice == that.acquisitionPrice &&
                minimumPrice == that.minimumPrice &&
                Objects.equals(dayPrice, that.dayPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomCategoryPriceId, roomCategory, season, listPrice, acquisitionPrice, minimumPrice, dayPrice);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "RoomCategory", referencedColumnName = "RoomCategoryID", nullable = false)
    public RoomcategoryEntity getRoomcategoryByRoomCategory() {
        return roomcategoryByRoomCategory;
    }

    public void setRoomcategoryByRoomCategory(RoomcategoryEntity roomcategoryByRoomCategory) {
        this.roomcategoryByRoomCategory = roomcategoryByRoomCategory;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "Season", referencedColumnName = "SeasonID", nullable = false)
    public SeasonEntity getSeasonBySeason() {
        return seasonBySeason;
    }

    public void setSeasonBySeason(SeasonEntity seasonBySeason) {
        this.seasonBySeason = seasonBySeason;
    }
}
