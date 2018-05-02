package at.fhv.roomix.domain.reservation;

/**
 * Roomix
 * at.fhv.roomix.domain.reservation
 * PaymentType
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PaymentType {
    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
