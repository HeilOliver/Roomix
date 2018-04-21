package at.fhv.roomix.controller.reservation.model;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ArticlePojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

public class ArticlePojo {
    private int id;
    private String discription;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
