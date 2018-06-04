package at.fhv.roomix.webLogin.model.security;

public class FreeRoomPojo {
    private String categoryName;
    private int freeRooms;
    private  int categoryId;

    public FreeRoomPojo(int freeRooms,String categoryName,int categoryId){
        this.categoryName=categoryName;
        this.freeRooms=freeRooms;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
