package at.fhv.roomix.domain.reservation;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * Arrangement
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Arrangement {
    private int id;
    private int price;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
