package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Article", schema = "Roomix", catalog = "")
public class ArticleEntity {
    private int articleId;
    private String articleDescription;
    private String articleType;
    private int price;
    private Collection<ArrangementEntity> arrangementsByArticleId;
    private Collection<InvoicePositionEntity> invoicePositionsByArticleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ArticleID")
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "ArticleDescription")
    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    @Basic
    @Column(name = "Price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "ArticleType")
    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return articleId == that.articleId &&
                Objects.equals(articleDescription, that.articleDescription) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId, articleDescription, price);
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "articleByArticle")
    public Collection<ArrangementEntity> getArrangementsByArticleId() {
        return arrangementsByArticleId;
    }

    public void setArrangementsByArticleId(Collection<ArrangementEntity> arrangementsByArticleId) {
        this.arrangementsByArticleId = arrangementsByArticleId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "articleByArticle")
    public Collection<InvoicePositionEntity> getInvoicePositionsByArticleId() {
        return invoicePositionsByArticleId;
    }

    public void setInvoicePositionsByArticleId(Collection<InvoicePositionEntity> invoicePositionsByArticleId) {
        this.invoicePositionsByArticleId = invoicePositionsByArticleId;
    }
}
