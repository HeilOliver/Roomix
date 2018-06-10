package at.fhv.roomix.webLogin.model.request;

public class CategoryRequest {
    private int categoryID;
    private int amount;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
