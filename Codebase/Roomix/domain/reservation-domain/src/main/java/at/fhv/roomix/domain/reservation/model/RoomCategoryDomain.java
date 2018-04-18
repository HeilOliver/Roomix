package at.fhv.roomix.domain.reservation.model;


import at.fhv.roomix.domain.reservation.helper.OccupationStatusHelper;

public class RoomCategoryDomain {
    /* Database */
    private int roomCategoryId;
    private String categoryDescription;
    private OccupationStatusHelper occupation;

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


    public OccupationStatusHelper getOccupation() {
        return occupation;
    }

    public void setOccupation(OccupationStatusHelper occupation) {
        this.occupation = occupation;
    }

}
