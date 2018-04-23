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
    private String arrangementName;
    private String arrangementDescription;
    private int arrangementPrice;
    private ArticleEntity articleByArticle;
    private Collection<InvoicePositionEntity> invoicePositionsByArrangementId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "ArrangementName")
    public String getArrangementName() {
        return arrangementName;
    }

    public void setArrangementName(String arrangementName) {
        this.arrangementName = arrangementName;
    }

    @Basic
    @Column(name = "ArrangementDescription")
    public String getArrangementDescription() {
        return arrangementDescription;
    }

    public void setArrangementDescription(String arrangementDescription) {
        this.arrangementDescription = arrangementDescription;
    }

    @Basic
    @Column(name = "ArrangementPrice")
    public int getArrangementPrice() {
        return arrangementPrice;
    }

    public void setArrangementPrice(int arrangementPrice) {
        this.arrangementPrice = arrangementPrice;
    }
}
