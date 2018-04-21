package at.fhv.roomix.domain.guest.model;


import java.time.LocalDate;

public class RoomCategoryDomain {
    /* Database */
    private int roomCategoryId;
    private String categoryDescription;

    private RoomCategoryMetaData metaData;

    public int getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(int roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


    public RoomCategoryMetaData getMetaData() {
        return metaData;
    }

    public void setCategoryMetaData(LocalDate start, LocalDate end, GuestDomain guest){

    }

}
