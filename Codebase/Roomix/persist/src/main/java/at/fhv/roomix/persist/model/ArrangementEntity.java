package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Arrangement", schema = "Roomix", catalog = "")
public class ArrangementEntity {
    private int arrangementId;
    private int article;
    private Integer discount;
    private ArticleEntity articleByArticle;
    private Collection<InvoicePositionEntity> invoicePositionsByArrangementId;

    @Id
    @Column(name = "ArrangementID")
    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    @Basic
    @Column(name = "Article", insertable = false, updatable = false)
    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    @Basic
    @Column(name = "Discount")
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrangementEntity that = (ArrangementEntity) o;
        return arrangementId == that.arrangementId &&
                article == that.article &&
                Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(arrangementId, article, discount);
    }

    @ManyToOne
    @JoinColumn(name = "Article", referencedColumnName = "ArticleID", nullable = false)
    public ArticleEntity getArticleByArticle() {
        return articleByArticle;
    }

    public void setArticleByArticle(ArticleEntity articleByArticle) {
        this.articleByArticle = articleByArticle;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "arrangementByArrangement")
    public Collection<InvoicePositionEntity> getInvoicePositionsByArrangementId() {
        return invoicePositionsByArrangementId;
    }

    public void setInvoicePositionsByArrangementId(Collection<InvoicePositionEntity> invoicePositionsByArrangementId) {
        this.invoicePositionsByArrangementId = invoicePositionsByArrangementId;
    }
}
