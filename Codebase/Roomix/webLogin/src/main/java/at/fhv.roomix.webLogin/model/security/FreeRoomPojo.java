package at.fhv.roomix.webLogin.model.security;

public class FreeRoomPojo {
    private String categoryName;
    private int freeRooms;

    public FreeRoomPojo(int freeRooms,String categoryName){
        this.categoryName=categoryName;
        this.freeRooms=freeRooms;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(int freeRooms) {
        this.freeRooms = freeRooms;
    }
}
