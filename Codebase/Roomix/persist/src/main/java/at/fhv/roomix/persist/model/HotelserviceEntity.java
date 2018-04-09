package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hotelservice", schema = "roomix", catalog = "")
public class HotelserviceEntity {
    private int hotelServiceId;
    private int article;
    private ArticleEntity articleByArticle;

    @Id
    @Column(name = "HotelServiceID")
    public int getHotelServiceId() {
        return hotelServiceId;
    }

    public void setHotelServiceId(int hotelServiceId) {
        this.hotelServiceId = hotelServiceId;
    }

    @Basic
    @Column(name = "Article", insertable = false, updatable = false)
    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelserviceEntity that = (HotelserviceEntity) o;
        return hotelServiceId == that.hotelServiceId &&
                article == that.article;
    }

    @Override
    public int hashCode() {

        return Objects.hash(hotelServiceId, article);
    }

    @ManyToOne
    @JoinColumn(name = "Article", referencedColumnName = "ArticleID", nullable = false)
    public ArticleEntity getArticleByArticle() {
        return articleByArticle;
    }

    public void setArticleByArticle(ArticleEntity articleByArticle) {
        this.articleByArticle = articleByArticle;
    }
}
