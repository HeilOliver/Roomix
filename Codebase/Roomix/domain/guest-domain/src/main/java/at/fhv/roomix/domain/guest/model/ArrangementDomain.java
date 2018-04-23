package at.fhv.roomix.domain.guest.model;

public class ArrangementDomain {

    private int arrangementId;
    private int article;
    private Integer discount;
    private String arrangementName;
    private String arrangementDescription;
    private int arrangementPrice;

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getArrangementName() {
        return arrangementName;
    }

    public void setArrangementName(String arrangementName) {
        this.arrangementName = arrangementName;
    }

    public String getArrangementDescription() {
        return arrangementDescription;
    }

    public void setArrangementDescription(String arrangementDescription) {
        this.arrangementDescription = arrangementDescription;
    }

    public int getArrangementPrice() {
        return arrangementPrice;
    }

    public void setArrangementPrice(int arrangementPrice) {
        this.arrangementPrice = arrangementPrice;
    }
}
