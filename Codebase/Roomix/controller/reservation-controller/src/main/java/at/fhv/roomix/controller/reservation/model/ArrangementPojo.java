package at.fhv.roomix.controller.reservation.model;

import java.util.Collection;
import java.util.HashSet;

public class ArrangementPojo {
    private int id;
    private int discount;
    private Collection<ArticlePojo> article;

    public ArrangementPojo(){
        article = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Collection<ArticlePojo> getArticle() {
        return article;
    }

    public void setArticle(Collection<ArticlePojo> article) {
        this.article = article;
    }
}
