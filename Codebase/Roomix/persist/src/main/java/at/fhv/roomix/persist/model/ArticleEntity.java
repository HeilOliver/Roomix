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
    private Integer amount;
    private Collection<ArrangementEntity> arrangementsByArticleId;
    private Collection<InvoicePositionEntity> invoicePositionsByArticleId;

    @Id
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
    @Column(name = "Amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return articleId == that.articleId &&
                Objects.equals(articleDescription, that.articleDescription) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId, articleDescription, amount);
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
