package at.fhv.roomix.domain.guest.model;

public class RoomCategoryPriceDomain {

    private int roomCategoryPriceId;
    private int roomCategory;
    private int listPrice;
    private int acquisitionPrice;
    private int minimumPrice;
    private Integer dayPrice;
    private RoomCategoryDomain roomCategoryByRoomCategory;
    private SeasonDomain seasonBySeason;

    public int getRoomCategoryPriceId() {
        return roomCategoryPriceId;
    }

    public void setRoomCategoryPriceId(int roomCategoryPriceId) {
        this.roomCategoryPriceId = roomCategoryPriceId;
    }

    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public int getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(int acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public Integer getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Integer dayPrice) {
        this.dayPrice = dayPrice;
    }

    public RoomCategoryDomain getRoomCategoryByRoomCategory() {
        return roomCategoryByRoomCategory;
    }

    public void setRoomCategoryByRoomCategory(RoomCategoryDomain roomCategoryByRoomCategory) {
        this.roomCategoryByRoomCategory = roomCategoryByRoomCategory;
    }

    public SeasonDomain getSeasonBySeason() {
        return seasonBySeason;
    }

    public void setSeasonBySeason(SeasonDomain seasonBySeason) {
        this.seasonBySeason = seasonBySeason;
    }
}
