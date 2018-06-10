package at.fhv.roomix.webLogin.model.request;

/**
 * Roomix
 * at.fhv.roomix.webLogin.model.request
 * PriceRequest
 * 10.06.2018 sge
 * <p>
 * Enter Description here
 */
public class PriceRequest {
    private PriceCalc[] priceRequests;

    public PriceCalc[] getPriceRequests() {
        return priceRequests;
    }

    public void setPriceRequests(PriceCalc[] priceRequests) {
        this.priceRequests = priceRequests;
    }
}
