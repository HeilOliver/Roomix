package at.fhv.roomix.controller.reservation.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.model
 * ArrangementPojo
 * 20/04/2018 Robert Schmitzer
 * <p>
 * Enter Description here
 */

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
